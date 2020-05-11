package com.ibm.sdet.model;

import com.ibm.sdet.util.BeneficiaryStatus;

public class Beneficiary {
	private Integer id;
	private String name;
	private String city;
	private int age;
	private double monthlyIncome;
	private String occupation;
	private String otherNote;
	private String status;
	
	public Beneficiary() {}
	
	public Beneficiary(Integer id, String name, String city, int age, double monthlyIncome, String occupation,
			String otherNote, String status)
	{
		this.id = id;
		this.name = name;
		this.city = city;
		this.age = age;
		this.monthlyIncome = monthlyIncome;
		this.occupation = occupation;
		this.otherNote = otherNote;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOtherNote() {
		return otherNote;
	}

	public void setOtherNote(String otherNote) {
		this.otherNote = otherNote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String beneficiaryStatus) {
		this.status = beneficiaryStatus;
	}
	
	
}
