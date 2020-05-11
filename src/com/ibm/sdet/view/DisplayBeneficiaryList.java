package com.ibm.sdet.view;

import java.util.List;

import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.util.Constants;

public class DisplayBeneficiaryList {
	
	public void DisplayABeneficiary(Beneficiary ben) {
		System.out.format("%5s%20s%20s","ID","NAME","ADDRESS");
		System.out.format("%5s%10s%22s","AGE","INCOME","OCCUPATION");
		System.out.format("%30s%12s","NOTE","STATUS");
		System.out.print("\n");
		System.out.format("%5s%20s%20s",ben.getId(),ben.getName(),ben.getCity());
		System.out.format("%5s%10s%22s",ben.getAge(),ben.getMonthlyIncome(),ben.getOccupation());
		System.out.format("%30s%12s", ben.getOtherNote(),ben.getStatus());
	}
	
	public void DisplayPerStatus(List<Beneficiary> ben, String status) {
		System.out.println("\nList of " + status + " beneficiaries.\n");
		System.out.format("%5s%20s%20s","ID","NAME","ADDRESS");
		System.out.format("%5s%10s%22s","AGE","INCOME","OCCUPATION");
		System.out.format("%30s%12s","NOTE","STATUS");
		for (Beneficiary bene : ben) {
			if (bene.getStatus().contains(status)) {
				System.out.print("\n");
				System.out.format("%5s%20s%20s",bene.getId(),bene.getName(),bene.getCity());
				System.out.format("%5s%10s%22s",bene.getAge(),bene.getMonthlyIncome(),bene.getOccupation());
				System.out.format("%30s%12s", bene.getOtherNote(),bene.getStatus());
			}
		}
	}
	
	public void DisplayAll(List<Beneficiary> ben) {
		System.out.println("\nList of beneficiaries.\n");
		System.out.format("%5s%20s%20s","ID","NAME","ADDRESS");
		System.out.format("%5s%10s%22s","AGE","INCOME","OCCUPATION");
		System.out.format("%30s%12s","NOTE","STATUS");
		for (Beneficiary bene : ben) {
			System.out.print("\n");
			System.out.format("%5s%20s%20s",bene.getId(),bene.getName(),bene.getCity());
			System.out.format("%5s%10s%22s",bene.getAge(),bene.getMonthlyIncome(),bene.getOccupation());
			System.out.format("%30s%12s", bene.getOtherNote(),bene.getStatus());
		}
	}
}
