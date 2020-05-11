package com.ibm.sdet.processor;

import com.ibm.sdet.model.Beneficiary;
import com.ibm.sdet.util.BeneficiaryStatus;
import com.ibm.sdet.util.Constants;

public class StatusValidatorProcessor {
	
	public String ValidateStatus(Beneficiary beneficiary) {

		String finalBeneficiaryStatus = Constants.PENDING;
		
		if (beneficiary.getAge() >= Constants.SENIORCITIZEN
			|| beneficiary.getOtherNote().matches(Constants.APPROVED_NOTE_REGEX)
			|| beneficiary.getOccupation().matches(Constants.APPROVED_OCCUPATION_REGEX)
			|| (beneficiary.getMonthlyIncome() <= Constants.MAX_APPROVED_INCOME
			    && beneficiary.getMonthlyIncome() > Constants.ZERO)
			|| beneficiary.getOccupation() == Constants.NONE) {
			finalBeneficiaryStatus = Constants.APPROVED;
		}
		
		if (beneficiary.getOtherNote().contains(Constants.ECQVIOLATOR)
			|| beneficiary.getMonthlyIncome() >= Constants.RICH_PEOPLE_INCOME) {
			finalBeneficiaryStatus = Constants.DENIED;
		}
		
		return finalBeneficiaryStatus;
	}
}
