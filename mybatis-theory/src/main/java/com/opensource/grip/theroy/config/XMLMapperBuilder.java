package com.opensource.grip.theroy.config;

import com.opensource.grip.theroy.pojo.Configuration;
import com.opensource.grip.theroy.pojo.MappedStatements;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author wangmin
 */
public class XMLMapperBuilder {
    private final Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElement = rootElement.selectNodes("//select");
        for (Element element : selectElement) {
            MappedStatements mappedStatements = new MappedStatements();
            String id = element.attributeValue("id");
            final String resultType = element.attributeValue("resultType");
            final String paramType = element.attributeValue("paramType");
            String sqlText = element.getTextTrim();
            mappedStatements.setId(id);
            mappedStatements.setResultType(resultType);
            mappedStatements.setParamType(paramType);
            mappedStatements.setSql(sqlText);
            String key = namespace + "." + id;
            configuration.getMappedStatementsMap().put(key, mappedStatements);
        }
    }
}
