package com.sxz.maxbi;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 private String userName;
	    private String password;
	    private String phone;
	    private String email;
	    private String sex;
	    private String age;

	    public Person(){}

	    public Person(String userName, String password, String phone, String email,
	            String sex, String age) {
	        super();
	        this.userName = userName;
	        this.password = password;
	        this.phone = phone;
	        this.email = email;
	        this.sex = sex;
	        this.age = age;
	    }
	    @Override
	    public String toString() {
	        return "Person [userName=" + userName + ", password=" + password
	                + ", phone=" + phone + ", email=" + email + ", sex=" + sex
	                + ", age=" + age + "]";
	    }
	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getPhone() {
	        return phone;
	    }
	    public void setPhone(String phone) {
	        this.phone = phone;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getSex() {
	        return sex;
	    }
	    public void setSex(String sex) {
	        this.sex = sex;
	    }
	    public String getAge() {
	        return age;
	    }
	    public void setAge(String age) {
	        this.age = age;
	    }

	
	

}
