package calculations;

import helpers.MortgageLoanCalculatorHelper;

public class MortgageLoanCalculatorAnnuity {

	MortgageLoanCalculatorHelper helper = new MortgageLoanCalculatorHelper();
	
	public int loanAmountToPayPerMonthAnnuity(double loanAmount, int periodMonths) {
		double amountToPayPerMonthAnnuity = loanAmount * helper.annuityCoeficient(periodMonths);
		return (int) amountToPayPerMonthAnnuity;
	}
	
	public int loanAmountAnnuity(int monthlyInstallment, int periodMonths) {
		double loanAmount = monthlyInstallment / helper.annuityCoeficient(periodMonths);
		return (int) loanAmount;
	}

	public int maxLoanAmountAnnuity(int incomeAmount, int liabilitiesAmount, int adultNumber, int childrenNumber, String city) {
		double netIncome = helper.netIncome(incomeAmount, liabilitiesAmount, adultNumber, childrenNumber, city);
		double maxLoanAmountAnnuity = netIncome / helper.annuityCoeficient(360);
		return (int)maxLoanAmountAnnuity;
	}

}
