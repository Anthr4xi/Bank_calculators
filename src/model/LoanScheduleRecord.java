package model;

public class LoanScheduleRecord {

	String period;
	String installmentAmount;
	String principle;
	String interestAmount;
	String leftLoan;
	
	public LoanScheduleRecord(String period, String installmentAmount, String principle, String interestAmount, String leftLoan) {
		this.period = period;
		this.installmentAmount = installmentAmount;
		this.principle = principle;
		this.interestAmount = interestAmount;
		this.leftLoan = leftLoan;
	
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public String getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getLeftLoan() {
		return leftLoan;
	}

	public void setLeftLoan(String leftLoan) {
		this.leftLoan = leftLoan;
	}
	
}
