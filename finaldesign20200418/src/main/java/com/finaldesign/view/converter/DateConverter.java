package com.finaldesign.view.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String dateTimeStr) {
		try {
			long dateTime = Long.parseLong(dateTimeStr);
			return new Date(dateTime);
		} catch (Exception e) {
			return null;
		}
	}

}
