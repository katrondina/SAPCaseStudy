package com.ibm.sdet.processor;

import java.sql.SQLException;
import java.util.List;
import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.model.BeneficiaryCRUDImpl;
import com.ibm.sdet.view.DisplayBeneficiary;

public class DuplicateProcessor {
	
	public void DuplicateValidator(Beneficiary ben) throws SQLException {
		String enteredName = ben.getName().trim().replaceAll(" +", " ");
		List<Beneficiary> benn = BeneficiaryCRUDImpl.ReadSapTblByName(enteredName);
		DisplayBeneficiary display = new DisplayBeneficiary();
		if(benn.size()>0) {
			System.out.print("\nRECORD REJECTED ==> REASON: " + ben.getName() + " is a duplicate of ");
			for (int j=0; j<benn.size();j++) {
				display.DisplayABeneficiary(benn.get(j));
			}
			//System.out.println("\n");
		} else {
			BeneficiaryCRUDImpl.InsertABeneficiary(ben);
		}
	}
}
