package com.opensource.grip.theroy.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.opensource.grip.theroy.io.Resources;
import com.opensource.grip.theroy.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author wangmin
 */
public class XMLConfigBuilder {

    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件进行解析封装 Configuration
     *
     * @param inputStream 输入流
     * @return Configuration
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        //<Configuration>
        Element rootElement = document.getRootElement();
        //解析config.xml
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : propertyList) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }
        //创建连接池对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);
        //mapper.xml解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("//resource");
            InputStream mapperInputStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parseMapper(mapperInputStream);
        }
        return configuration;
    }
}
