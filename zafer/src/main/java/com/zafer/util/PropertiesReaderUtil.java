package com.zafer.util;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesReaderUtil {
    private final String configFileName;

    public PropertiesReaderUtil(String configFileName) {
        this.configFileName = configFileName;
    }

    public Properties read() throws IOException {
        String folderDir = "/src/main/resources/";
        String fileExtension = ".properties";
        log.info("reading config file with name : {}{}{}",folderDir,configFileName,fileExtension);
        String filePath = String.format("%s%s%s%s",System.getProperty("user.dir"),folderDir,configFileName,fileExtension);
        log.info("properties file path : {}",filePath);
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(filePath);
        properties.load(inputStream);
        log.info("{}{}{} config file read completed",folderDir,configFileName,fileExtension);
        return properties;
    }
}
