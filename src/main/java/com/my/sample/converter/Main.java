package com.my.sample.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2017"));
	}

}
