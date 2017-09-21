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
		
		String str = "bill:";
		
		String[] arr =str.split(":");
		System.out.println(arr.length);
		System.out.println(arr[0]+"---"+arr[1]);
//		byte[]   bytesEncoded = null;
////        byte[]   bytesEncoded = Base64.getEncoder().encode(str .getBytes());
//        System.out.println("ecncoded value is " + new String(bytesEncoded ));
//        
//        byte[]   bytesdecoded = Base64.getDecoder().decode(new String(bytesEncoded ));
//        System.out.println("decoded value is " + new String(bytesdecoded ));
        
	}

}
