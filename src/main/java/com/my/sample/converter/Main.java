package com.my.sample.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.my.sample.util.AppConstants;

public class Main {

	public static void main(String[] args) throws ParseException {
		Integer year = 2017;
		Date fromDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse("1-1-" + year);
		Date toDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse("31-12-" + year);
		System.out.println(fromDate);
		System.out.println(toDate);
	}

}
