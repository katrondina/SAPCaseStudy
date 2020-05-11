package com.ibm.sdet.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.model.BeneficiaryInsertImpl;
import com.ibm.sdet.model.BeneficiaryCRUDImpl;
import com.ibm.sdet.model.FileLoader;
import com.ibm.sdet.processor.InputProcessor;
import com.ibm.sdet.util.Constants;

public class SAPRunnerMain {

	public static void main(String[] args) {
		
		BeneficiaryCRUDImpl beneficiaryReadImpl = new BeneficiaryCRUDImpl();
		char action = 'Y';
		//FileLoader file2 = new FileLoader();
		//file2.readBeneficiaryFile();
		InputProcessor inputProcessor = new InputProcessor();
		boolean validAction = false;
		
		if (Constants.INITIAL_LOAD) {
			FileLoader file = new FileLoader();
			BeneficiaryInsertImpl beneficiaryImpl = new BeneficiaryInsertImpl();
			beneficiaryImpl.insertBeneficiary(file.readBeneficiaryFile()); }
		
		do {
			try {
				System.out.println("\n\nMain Menu");
				System.out.println("1 Display a record");
				System.out.println("2 Display list of records");
				System.out.println("3 update pending beneficiaries");
				System.out.println("4 insert a record");
				System.out.println("5 delete a record");
				System.out.println("q to exit");
				System.out.print("input: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				action = (char)br.read();
				validAction = inputProcessor.UserInputValidator(action);
				
				
				if(validAction) {
					System.out.println("\n\nnice");
				}
				
				
			}catch (Exception e) {
				System.out.println("Invalid input.");
			}
		} while(action != 'q');
		System.out.println("You have exited the pgm.");
		
		//read
		if (Constants.INITIAL_LOAD) {
		try {
			System.out.println("reading?");
			Beneficiary ben = beneficiaryReadImpl.ReadSapTbl(5);
		} catch (SQLException e) {
			System.out.println(e);
		}}
		
		
		/*read
		 * try { DisplayBeneficiaryList displayBeneficiaryList = new
		 * DisplayBeneficiaryList();
		 * displayBeneficiaryList.DisplayPerStatus(beneficiaryReadImpl.ReadSapTblAll());
		 * } catch (SQLException e) { System.out.println(e); }
		 */
	}

}
