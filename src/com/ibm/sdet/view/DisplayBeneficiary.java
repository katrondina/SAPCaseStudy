package com.ibm.sdet.view;

import java.util.List;
import com.ibm.sdet.model.Beneficiary;
public class DisplayBeneficiary {
	
	
	public void DisplayHeader() {
		System.out.format("%5s%20s%20s","ID","NAME","ADDRESS");
		System.out.format("%5s%10s%22s","AGE","INCOME","OCCUPATION");
		System.out.format("%30s%12s","NOTE","STATUS");
		System.out.print("\n");
	}
	
	public void DisplayABeneficiary(Beneficiary ben) {
		System.out.format("%5s%20s%20s",ben.getId(),ben.getName(),ben.getCity());
		System.out.format("%5s%10s%22s",ben.getAge(),ben.getMonthlyIncome(),ben.getOccupation());
		System.out.format("%30s%12s", ben.getOtherNote(),ben.getStatus());
		System.out.print("\n");
	}
	
	public void DisplayPerStatus(List<Beneficiary> ben, String status) {
		System.out.println("\nList of " + status + " beneficiaries.\n");
		int perStatusCounter = 0;
		DisplayHeader();
		for (Beneficiary bene : ben) {
			if (bene.getStatus().contains(status)) {
				DisplayABeneficiary(bene);
				perStatusCounter = perStatusCounter + 1;
			}
		}
		System.out.println("\n# of " + status + " beneficiaries: " + perStatusCounter);
	}
	
	public void DisplayAll(List<Beneficiary> ben) {
		System.out.println("\nList of beneficiaries.\n");
		int benCounter = 0;
		DisplayHeader();
		for (Beneficiary bene : ben) {
			DisplayABeneficiary(bene);
			benCounter = benCounter + 1;
		}
		System.out.println("\n# of beneficiaries retrieved: " + benCounter);
	}
}
