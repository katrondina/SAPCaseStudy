package com.ibm.sdet.processor;

import java.util.Comparator;

import com.ibm.sdet.model.Beneficiary;

 public class AgeComparator implements Comparator<Beneficiary> {
	
	@Override
	public int compare(Beneficiary b1, Beneficiary b2) {
		if(b1.getAge() == b2.getAge()) {return 0;}
		else if(b1.getAge()>b2.getAge()) {return 1;}
		else {return -1;}
	}
}
