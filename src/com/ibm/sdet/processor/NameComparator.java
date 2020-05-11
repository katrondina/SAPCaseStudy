package com.ibm.sdet.processor;

import java.util.Comparator;
import com.ibm.sdet.model.Beneficiary;

public class NameComparator implements Comparator<Beneficiary>{

	@Override
	public int compare(Beneficiary b1, Beneficiary b2) {
		return b1.getName().compareTo(b2.getName());
	}
	
}
