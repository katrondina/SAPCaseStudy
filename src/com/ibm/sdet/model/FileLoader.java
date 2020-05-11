package com.ibm.sdet.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.sdet.processor.StatusValidatorProcessor;
import com.ibm.sdet.util.Constants;

public class FileLoader {

	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
	private StatusValidatorProcessor statusValidator = new StatusValidatorProcessor();
	private int rowCounter = 0;

	public List<Beneficiary> readBeneficiaryFile() {
		System.out.println("Reading " + Constants.FILENAME + " ...");
		FileInputStream fis;
		try {
			fis = new FileInputStream(Constants.FILENAME);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue;
				Beneficiary ben = new Beneficiary();
				for (int j = 0; j < Constants.NOOFCELLPERROW; j++) {
					if (j == 0) {
						ben.setName(row.getCell(j).getStringCellValue());
						//System.out.println("name : " + ben.getName());
					}
					if (j == 1) {
						ben.setCity(row.getCell(j).getStringCellValue());
						//System.out.println("city : " + ben.getCity());
					}
					row.getCell(j);
					if (j == 2) {
						int ageint = (int) row.getCell(j).getNumericCellValue();
						ben.setAge(ageint);
						//System.out.println("age : " + ben.getAge());
					}
					if (j == 3) {
						String income = row.getCell(j).toString();
						if (income == "") {
							//System.out.println("Cell is empty, updated the monthly income to 0.0.");
							ben.setMonthlyIncome(0.0);
							//System.out.println("monthly Income : " + ben.getMonthlyIncome());
						} else {
							String formattedIncome = income.replaceAll(", ", "");
							double finalIncome = Double.valueOf(formattedIncome);
							ben.setMonthlyIncome(finalIncome);
							//System.out.println("monthly Income : " + ben.getMonthlyIncome());
						}
					}
					if (j == 4) {
						ben.setOccupation(row.getCell(j).getStringCellValue());
						//System.out.println("occupation : " + ben.getOccupation());
					}
					if (j == 5) {
						ben.setOtherNote(row.getCell(j).getStringCellValue());
						//System.out.println("Note : " + ben.getOtherNote());
					}
					
				}
				ben.setStatus(statusValidator.ValidateStatus(ben));
				beneficiaries.add(ben);
				rowCounter = rowCounter + 1;
			}
			System.out.println("Nos of records read: " + rowCounter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beneficiaries;
	}
}
