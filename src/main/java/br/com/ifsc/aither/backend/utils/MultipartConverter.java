package br.com.ifsc.aither.backend.multipart;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class MultipartConverter implements Converter<MultipartFile, byte[]> {

	@SneakyThrows
	@Override
	public byte[] convert(MultipartFile source) {
		return source.getBytes();
	}
}
