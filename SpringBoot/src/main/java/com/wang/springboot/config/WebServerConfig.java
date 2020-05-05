package com.wang.springboot.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Title: WebServerConfig
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午3:22:35 @version 1.0
 */
@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class })
public class WebServerConfig {

	@Value("${http.port}")
	private int httpPort;

	@Bean
	public Connector connector() {

		Connector connector = new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory tswf = new TomcatServletWebServerFactory();
		tswf.addAdditionalTomcatConnectors(connector());
		return tswf;
	}
}
