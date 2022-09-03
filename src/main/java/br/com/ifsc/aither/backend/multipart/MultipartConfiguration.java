package br.com.ifsc.aither.backend.multipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfiguration {

	@Value("${spring.servlet.multipart.max-file-size:10MB}")
	private String multipartMaxFileSize;

	@Value("${spring.servlet.multipart.max-request-size:100MB}")
	private String multipartMaxResquestSize;

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();

		factory.setMaxFileSize(DataSize.parse(multipartMaxFileSize));
		factory.setMaxRequestSize(DataSize.parse(multipartMaxResquestSize));

		return factory.createMultipartConfig();
	}
}
