package com.my.sample.converter;

import java.text.ParseException;

import com.my.sample.config.security.BCryptPasswordEncoder;

//$2a$10$EAhNUm1q1xq5VRj0g6veqeZX8a9Ug.CPHLT4tXCA3LLn9R6yrvBNS
//$2a$10$qWi6nHNrXy3ka/ng.ZmQw.J5hlRh6qmnOr9A9QQuPpKyt.E3/5Sha
public class Main {

	public static void main(String[] args) throws ParseException {
		String password = "123456789";
		String hashedPassword1 = new BCryptPasswordEncoder().encode(password);
		System.out.println(hashedPassword1);
		// Integer year = 2017;
		// Date fromDate = new
		// SimpleDateFormat(AppConstants.DATE_FORMAT).parse("1-1-" + year);
		// Date toDate = new
		// SimpleDateFormat(AppConstants.DATE_FORMAT).parse("31-12-" + year);
		// System.out.println(fromDate);
		// System.out.println(toDate);
		//
		// String str = "bill:";
		//
		// String[] arr =str.split(":");
		// System.out.println(arr.length);
		// System.out.println(arr[0]+"---"+arr[1]);
		// byte[] bytesEncoded = null;
		//// byte[] bytesEncoded = Base64.getEncoder().encode(str .getBytes());
		// System.out.println("ecncoded value is " + new String(bytesEncoded ));
		//
		// byte[] bytesdecoded = Base64.getDecoder().decode(new
		// String(bytesEncoded ));
		// System.out.println("decoded value is " + new String(bytesdecoded ));

	}

}
