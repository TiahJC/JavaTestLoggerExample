package com.example.file.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum MyBatisUtil {
    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    //Default constructor
    private MyBatisUtil() {
        String resource = "mybatis-config.xml";
        String propertiesFileName = "application.properties";
        InputStream inputStream;
        try {
            InputStream reader = getFileFromResourceAsStream(propertiesFileName);
            // create properties object
            Properties p = new Properties();
            // Add a wrapper around reader object
            p.load(reader);

            inputStream = getFileFromResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get a file from the resources folder
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
    public SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
