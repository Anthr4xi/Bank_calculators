package calculations;


import helpers.MortgageLoanCalculatorHelper;

public class MortgageLoanCalculatorLinear {

	MortgageLoanCalculatorHelper helper = new MortgageLoanCalculatorHelper();


	public int maxLoanAmountLinear(int incomeAmount, int liabilitiesAmount, int adultNumber, int childrenNumber,
			String city) {
		MortgageLoanCalculatorAnnuity annuity = new MortgageLoanCalculatorAnnuity();
		int maxLoanAnnuity = annuity.maxLoanAmountAnnuity(incomeAmount, liabilitiesAmount, adultNumber, childrenNumber,
				city);
		double maxLoanLinear = maxLoanAnnuity / 1.248;
		return (int) maxLoanLinear;
	}

	public int loanAmountToPayPerMonthLinear(double loanAmount, int periodMonths) {
		double amountToPayPerMonthLinear = (loanAmount / periodMonths) + (loanAmount * helper.getInterestRate() / 12);
		return (int) amountToPayPerMonthLinear;
	}

	public int loanAmountLinear(int monthlyInstallment, int periodMonths) {
		double loanAmount = monthlyInstallment * (periodMonths / 12)
				/ (0.08575 + (0.00241654460343962 * (periodMonths / 12 - 1)));
		return (int) loanAmount;
	}



}
