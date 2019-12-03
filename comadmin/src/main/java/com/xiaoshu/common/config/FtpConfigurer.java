package com.xiaoshu.common.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xiaoshu.common.util.FtpHandler;
/*
 配置类：配置ftp信息
 */
@Configuration
public class FtpConfigurer {
	
	@Bean
    public FtpHandler ftpHandler() throws IOException {
		String filePath = System.getProperty("user.dir") + "/config/config.properties";
    	InputStream in=new BufferedInputStream(new FileInputStream(filePath));
        Properties properties = new Properties();
        properties.load(in);
        String interHost = properties.getProperty("inter.host");
        int interPort = Integer.valueOf(properties.getProperty("inter.port"));
        String interUsername = properties.getProperty("inter.username");
        String interPassword = properties.getProperty("inter.password");
        return new FtpHandler(interHost, interPort, interUsername, interPassword);
    }
}
