package helpers;

import calculations.Variables;

public class MortgageLoanCalculatorHelper extends Variables{
	
	public double netIncome(int incomeAmount, int liabilitiesAmount, int adultNumber, int childrenNumber, String city) {
		int adultLivingPrice = 0;
		int childrenLivingPrice = 0;
		
		if (city.equalsIgnoreCase("Vilnius")) {
			adultLivingPrice = 370;
			childrenLivingPrice = 150;
		}
		if (city.equalsIgnoreCase("Kaunas") || city.equalsIgnoreCase("Klaipeda")) {
			adultLivingPrice = 340;
			childrenLivingPrice = 130;
		}
		if (city.equalsIgnoreCase("Other")) {
			adultLivingPrice = 300;
			childrenLivingPrice = 120;
		}
		
		double maxInstallment = ((incomeAmount - liabilitiesAmount - adultLivingPrice - (adultLivingPrice * (adultNumber - 1) * 0.6) - childrenLivingPrice * childrenNumber ));
		if ((maxInstallment + liabilitiesAmount) > incomeAmount * 0.4)
			maxInstallment = (incomeAmount * 0.4) - liabilitiesAmount;
		if (maxInstallment < 0) 
			maxInstallment = 0;
		return maxInstallment;
	}
	
	public double annuityCoeficient (int periodMonths) {
		double firstResult = (getInterestRate() / 12) * Math.pow((1 + getInterestRate() / 12), periodMonths);
		double secondResult = Math.pow((1 + getInterestRate() / 12), (periodMonths)) - 1;
		double coeficient = firstResult / secondResult;
		return coeficient;
	}
	
	public int periodCalculation (boolean annuity, double loanAmount) {
		if(annuity) loanAmount = loanAmount / 1.248;
		double interest = loanAmount * getInterestRate() / 12;
		double periods = loanAmount / ((netIncome(incomeAmount, liabilitiesAmount, adultNumber, childrenNumber, city)) - interest);
		return (int) Math.round(periods/12);
	}
	
}
