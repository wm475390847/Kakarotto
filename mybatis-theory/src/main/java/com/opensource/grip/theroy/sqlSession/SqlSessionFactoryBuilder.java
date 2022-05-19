package com.opensource.grip.theroy.sqlSession;

import com.opensource.grip.theroy.config.XMLConfigBuilder;
import com.opensource.grip.theroy.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author wangmin
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //dom4j解析xml文件，将解析出来内容封装到configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        //创建sqlSessionFactory对象
        return new DefaultSqlSessionFactory(configuration);
    }
}
