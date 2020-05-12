package com.ibm.sdet.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import com.ibm.sdet.model.FileLoader;
import com.ibm.sdet.processor.UserInputProcessor;
import com.ibm.sdet.util.Constants;

public class DisplayMainMenu {

	public static void main(String[] args) throws SQLException {
		
		String action = "Y";
		UserInputProcessor inputProcessor = new UserInputProcessor();
		boolean validAction = false;
		
		//Initial load of xls file, if initial_load is true then this will execute.
		if (Constants.INITIAL_LOAD) {
			FileLoader file = new FileLoader();
			file.readBeneficiaryFile();
			//FileLoaderInsert beneficiaryImpl = new FileLoaderInsert();
			//FileLoaderInsert.insertBeneficiary(file.readBeneficiaryFile()); 
			}
		
		//displaying of main menu
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
				action = br.readLine();
				validAction = inputProcessor.UserInputValidator(action);
				if(validAction) {
					System.out.println("");
				}
			}catch (Exception e) {
				//System.out.println(e);
				System.out.println("Invalid input.");
			}
		} while(!action.equalsIgnoreCase("Q"));
		System.out.println("You have exited the pgm.");
	}
}