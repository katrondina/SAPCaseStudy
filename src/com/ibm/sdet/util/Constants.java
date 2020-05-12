package com.ibm.sdet.util;

public class Constants {
	
	public static final boolean INITIAL_LOAD = false;
	public static final String FILENAME = "SAP Case Study.xlsx";
	public static final String PENDING = "PENDING";
	public static final String APPROVED = "APPROVED";
	public static final String DENIED = "DENIED";
	public static final String NONE = "None";
	public static final String ECQVIOLATOR = "ECQ Violator";
	public static final String APPROVED_NOTE_REGEX = "^.*?(Single Parent|PWD|No Work No Pay|Non-Essential).*$";
	public static final String DENIED_NOTE_REGEX = "^.*?().*$";
	public static final String APPROVED_OCCUPATION_REGEX = "^.*?(Driver|None|Vendor).*$";
	public static final String DENIED_OCCUPATION_REGEX = "^.*?(CEO|Politician).*$";
	public static final int NOOFCELLPERROW = 6;
	public static final int SENIORCITIZEN = 65;
	public static final double MAX_APPROVED_INCOME = 5000;
	public static final double MIN_APPROVED_INCOME = 1;
	public static final double RICH_PEOPLE_INCOME = 20000;
	public static final double ZERO = 0;
	
	
	private Constants() {}
}
