package com.my.sample.data;

import java.io.Serializable;

public class SearchCustomerData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5425338236877796150L;
	String name;
    String mobile;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
