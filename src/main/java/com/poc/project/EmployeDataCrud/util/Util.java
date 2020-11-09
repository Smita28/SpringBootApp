package com.poc.project.EmployeDataCrud.util;

public class Util {

	public static String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	
	public static boolean isValidEmail(String email){
		return email.matches(regex);
	}
}
