package com.my.sample.converter;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(new Date());

System.out.println(new Date());

Date date = new Date();
try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Date date1 = new Date();
System.out.println(date1.equals(date));
	}

}
