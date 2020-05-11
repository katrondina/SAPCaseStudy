package com.ibm.sdet.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.model.BeneficiaryCRUDImpl;
import com.ibm.sdet.processor.StatusValidatorProcessor;
import com.ibm.sdet.processor.AddressComparator;
import com.ibm.sdet.processor.AgeComparator;
import com.ibm.sdet.processor.NameComparator;
import com.ibm.sdet.util.Constants;

public class SwitchDisplay {

	StatusValidatorProcessor statusValidator = new StatusValidatorProcessor();
	DisplayBeneficiaryList display = new DisplayBeneficiaryList();
	
	//for inserting a record
	public void DisplayInsertInstructions() throws IOException {
		String input = "N";
		int loopCounter = 1;
		Beneficiary ben = new Beneficiary();
		do {
		if(loopCounter == 1) {
			System.out.println("Please enter details below.");
			System.out.print("Enter Name: ");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			String enteredName = br1.readLine();
			ben.setName(enteredName);
			System.out.print("Enter City Address: ");
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			String enteredCity = br2.readLine();
			ben.setCity(enteredCity);
			System.out.print("Enter Age: ");
			BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
			String enteredAgeString = br3.readLine();
			int enteredAge = Integer.parseInt(enteredAgeString);
			ben.setAge(enteredAge);
			System.out.print("Enter Monthly Income: ");
			BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
			String enteredIncomeString = br4.readLine();
			double enteredIncome = Double.parseDouble(enteredIncomeString);
			ben.setMonthlyIncome(enteredIncome);
			System.out.print("Enter Occupation: ");
			BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
			String enteredOccupation = br5.readLine();
			ben.setOccupation(enteredOccupation);
			System.out.print("Enter Note: ");
			BufferedReader br6 = new BufferedReader(new InputStreamReader(System.in));
			String enteredNote = br6.readLine();
			ben.setOtherNote(enteredNote);
			loopCounter = 2;
		}
		
		System.out.println("\n");
		
		display.DisplayHeader();
		display.DisplayABeneficiary(ben);
		
		System.out.println("\n\nID and Status will be updated by the system.");
		System.out.println("Are the details correct?");
		System.out.println("Y to insert this record");
		System.out.println("N to reenter details");
		System.out.println("B to go back to main menu");
		System.out.print("insert? ");
		BufferedReader br7 = new BufferedReader(new InputStreamReader(System.in));
		input = br7.readLine();
		
		if (input.equalsIgnoreCase("B")) {return;}
		else if(input.equalsIgnoreCase("N")) {loopCounter = 1;}
		else if(input.equalsIgnoreCase("Y")) {break;}
		else {System.out.println("=== Please enter valid input ===");}
		
		} while (!input.equalsIgnoreCase("Y"));
		
		ben.setStatus(statusValidator.ValidateStatus(ben));
		BeneficiaryCRUDImpl.InsertABeneficiary(ben);
	}
	
	// for deleting a record
	public void DisplayDeleteInstructions() throws IOException, SQLException {
		String input = "N";
		int idToDelete;
		do {
		DisplayBeneficiaryList display = new DisplayBeneficiaryList();
		System.out.print("Enter the id you want to delete: ");
		BufferedReader brDelete = new BufferedReader(new InputStreamReader(System.in));
		String idToDeleteString = brDelete.readLine();
		idToDelete = Integer.parseInt(idToDeleteString);
		Beneficiary bb = BeneficiaryCRUDImpl.ReadSapTbl(idToDelete);
		if(bb.getId().equals(null)) {
			System.out.println("No such id.");
			return;
		}
		display.DisplayABeneficiary(bb);
		System.out.println("\n\nReview the record you want to delete.\nDo you want to proceed?");
		System.out.println("Y for yes");
		System.out.println("N for no");
		System.out.println("B to go back to main menu");
		System.out.print("delete? ");
		BufferedReader br7 = new BufferedReader(new InputStreamReader(System.in));
		input = br7.readLine();
		if(input.equalsIgnoreCase("B")) {return;}
		}while(!input.equalsIgnoreCase("Y"));
		
		BeneficiaryCRUDImpl.DeleteSapTbl(idToDelete);
	}
	
	// for retrieving a record menu
	public void DisplayReadInstructions() throws SQLException, IOException {
		display.DisplayAll(BeneficiaryCRUDImpl.ReadSapTblAll());
	
			Scanner scan = new Scanner(System.in);
			String entered = "b";
			do {
			System.out.println("\n\n1 Display records per Status");
			System.out.println("2 Display sorted by Address");
			System.out.println("3 Display sorted by Age");
			System.out.println("4 Display sorted by Name");
			System.out.println("b to go back to main menu");
			System.out.print("how to proceed: ");
			entered = scan.next();
			if(entered.equalsIgnoreCase("b")) {return;}
			else if(entered.contentEquals("1")) {DisplayPerStatus();}
			else if(entered.contentEquals("2")) {DisplaySortedAddress();}
			else if(entered.contentEquals("3")) {DisplaySortedAge();}
			else if(entered.contentEquals("4")) {DisplaySortedName();}
			} while (!entered.equalsIgnoreCase("1")
					|| !entered.equalsIgnoreCase("2")
					|| !entered.equalsIgnoreCase("3")
					|| !entered.equalsIgnoreCase("4")
					|| !entered.equalsIgnoreCase("b"));
		
	}
	
	// for retrieving a record per status
	public void DisplayPerStatus() throws IOException, SQLException {
		System.out.println("Display list of beneficiaries per status.");
		System.out.print("approved/denied/pending: ");
		BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
		String statusEntered = brr.readLine();
		if (statusEntered.equalsIgnoreCase(Constants.APPROVED)
			|| statusEntered.equalsIgnoreCase(Constants.DENIED)
			|| statusEntered.equalsIgnoreCase(Constants.PENDING)) {
			display.DisplayPerStatus(BeneficiaryCRUDImpl.ReadSapTblAll(), statusEntered.toUpperCase());
		} else {System.out.println("Please enter a valid status.");}
	}
	
	// for retrieving a record
	public void DisplayARecordInstructions() throws IOException, SQLException {
		System.out.println("Let's display a record.");
		System.out.print("Please enter beneficiary id: ");
		BufferedReader brb = new BufferedReader(new InputStreamReader(System.in));
		String idEnteredString = brb.readLine();
		int idEntered = Integer.parseInt(idEnteredString);
		display.DisplayABeneficiary(BeneficiaryCRUDImpl.ReadSapTbl(idEntered));
	}
	
	// for updating a record
	public void DisplayUpdateARecordInstructions() throws SQLException, IOException {
		display.DisplayPerStatus(BeneficiaryCRUDImpl.ReadSapTblAll(), Constants.PENDING);
		System.out.print("\n\nPlease enter id of beneficiary you want to update: ");
		BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
		String idToUpdateString = br3.readLine();
		int idToUpdate = Integer.parseInt(idToUpdateString);
		System.out.print("Pending status changed to: ");
		BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
		String enteredNewStatus = br4.readLine();
		if (enteredNewStatus.equalsIgnoreCase(Constants.APPROVED)
			|| enteredNewStatus.equalsIgnoreCase(Constants.DENIED)) {
			if (BeneficiaryCRUDImpl.ReadSapTbl(idToUpdate).getStatus().contentEquals(Constants.PENDING)) {
				BeneficiaryCRUDImpl.UpdateSapTbl(idToUpdate, enteredNewStatus.toUpperCase());
				display.DisplayABeneficiary(BeneficiaryCRUDImpl.ReadSapTbl(idToUpdate));
			} else {
			  System.out.println("Status is already approved/denied. This record is not for update."); }
		} else {System.out.println("Please enter valid input.");}
	}
	
	// for displaying records sorted by age
	public void DisplaySortedAge() throws SQLException {
		List<Beneficiary> bene = BeneficiaryCRUDImpl.ReadSapTblAll();
		Collections.sort(bene, new AgeComparator());
		Iterator<Beneficiary> itr = bene.iterator();
		display.DisplayHeader();
		while(itr.hasNext()) {
			Beneficiary ben = itr.next();
			display.DisplayABeneficiary(ben);
		}
	}
	
	// for displaying records sorted by address
	public void DisplaySortedAddress() throws SQLException {
		List<Beneficiary> ben = BeneficiaryCRUDImpl.ReadSapTblAll();
		Collections.sort(ben, new AddressComparator());
		Iterator<Beneficiary> itr = ben.iterator();
		display.DisplayHeader();
		while(itr.hasNext()) {
			Beneficiary bene = itr.next();
			display.DisplayABeneficiary(bene);
		}
	}
	
	// for displaying records sorted by name
	public void DisplaySortedName() throws SQLException {
		List<Beneficiary> ben = BeneficiaryCRUDImpl.ReadSapTblAll();
		Collections.sort(ben, new NameComparator());
		Iterator<Beneficiary> itr = ben.iterator();
		display.DisplayHeader();
		while(itr.hasNext()) {
			Beneficiary bene = itr.next();
			display.DisplayABeneficiary(bene);
		}
	}
}
