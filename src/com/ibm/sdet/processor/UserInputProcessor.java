package com.ibm.sdet.processor;

import java.io.IOException;
import java.sql.SQLException;
import com.ibm.sdet.view.DisplayInstructions;

public class UserInputProcessor {
	DisplayInstructions switchDisplay = new DisplayInstructions();
	public Boolean UserInputValidator(String action) throws IOException, SQLException {
		switch(action) {
		case "1":
			switchDisplay.DisplayARecordInstructions();
			return true;
		case "2":
			switchDisplay.DisplayReadInstructions();
			return true;
		case "3":
			switchDisplay.DisplayUpdateARecordInstructions();
			return true;
		case "4":
			switchDisplay.DisplayInsertInstructions();
			return true;
		case "5":
			switchDisplay.DisplayDeleteInstructions();
			return true;
		default:
			return false;
		}
	}
}
