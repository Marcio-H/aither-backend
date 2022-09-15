package br.com.ifsc.aither.backend.config;

import br.com.ifsc.aither.backend.multipart.MultipartConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new MultipartConverter());
		WebMvcConfigurer.super.addFormatters(registry);
	}
}
