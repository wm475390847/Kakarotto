package com.opensource.grip.theroy.sqlSession;

import com.opensource.grip.theroy.config.BoundSql;
import com.opensource.grip.theroy.pojo.Configuration;
import com.opensource.grip.theroy.pojo.MappedStatements;
import com.opensource.grip.theroy.util.GenericTokenParser;
import com.opensource.grip.theroy.util.ParameterMapping;
import com.opensource.grip.theroy.util.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmin
 */
public class SimpleExecutor implements Executor {
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatements mappedStatements, Object... params) throws Exception {
        //1.注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        //2.获取sql语句 select * from t_case where id=#{id}
        //转换sql语句 select * from t_case where id=?，转换过程中还需要对#{}中的值解析存储
        String sql = mappedStatements.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //3.获取预处理对象
        final PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //4.设置参数
        final String parameterType = mappedStatements.getParamType();
        Class<T> parameterTypeClass = getClassType(parameterType);
        final List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            final ParameterMapping parameterMapping = parameterMappingList.get(i);
            final String content = parameterMapping.getContent();
            //反射
            final Field declaredField = parameterTypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            final Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        //5.执行sql
        final ResultSet resultSet = preparedStatement.executeQuery();
        final String resultType = mappedStatements.getResultType();
        final Class<Object> resultTypeClass = getClassType(resultType);
        final Object o = resultTypeClass.newInstance();
        final ArrayList<Object> objects = new ArrayList<>();
        //6.封装返回结果集
        while (resultSet.next()) {
            //元数据
            final ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                //字段名、值
                final String columnName = metaData.getColumnName(i);
                final Object object = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                final Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, object);
            }
            objects.add(o);
        }
        return (List<T>) objects;
    }

    private <T> Class<T> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            return (Class<T>) Class.forName(parameterType);
        }
        return null;
    }

    /**
     * 完成#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     *
     * @param sql sql
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        final String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        final List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parseSql, parameterMappings);
    }
}
