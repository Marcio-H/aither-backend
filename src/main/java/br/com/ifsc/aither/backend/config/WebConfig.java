package br.com.ifsc.aither.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ifsc.aither.backend.utils.MultipartConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new MultipartConverter());
		WebMvcConfigurer.super.addFormatters(registry);
	}
}
