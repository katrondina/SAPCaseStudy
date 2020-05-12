package com.ibm.sdet.processor;

import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.util.Constants;

public class StatusProcessor {
	
	public String ValidateStatus(Beneficiary beneficiary) {

		String finalBeneficiaryStatus = Constants.PENDING;
		
		// determine if status should be approved
		if (beneficiary.getAge() >= Constants.SENIORCITIZEN
			|| beneficiary.getOtherNote().matches(Constants.APPROVED_NOTE_REGEX)
			|| beneficiary.getOccupation().matches(Constants.APPROVED_OCCUPATION_REGEX)
			|| (beneficiary.getMonthlyIncome() <= Constants.MAX_APPROVED_INCOME
			    && beneficiary.getMonthlyIncome() > Constants.ZERO)
			|| beneficiary.getOccupation().equalsIgnoreCase(Constants.NONE)) {
			finalBeneficiaryStatus = Constants.APPROVED;
		}
		
		// determine if status should be denied
		if (beneficiary.getOtherNote().equalsIgnoreCase(Constants.ECQVIOLATOR)
			|| beneficiary.getMonthlyIncome() >= Constants.RICH_PEOPLE_INCOME) {
			finalBeneficiaryStatus = Constants.DENIED;
		}
		
		return finalBeneficiaryStatus;
	}
}
