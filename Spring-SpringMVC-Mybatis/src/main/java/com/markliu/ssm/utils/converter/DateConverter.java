package com.markliu.ssm.utils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义的日期类型转换器
 * Author: markliu
 * Time  : 16-8-24 下午1:13
 */
public class DateConverter implements Converter<String, Date> {

	/**
	 * 从字符串类型的日期转换成 Date 类型的日期
	 */
	public Date convert(String source) {

		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
