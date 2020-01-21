package calculations;

public class Variables {

	private double interestRate = 0.029;

	protected int adultNumber;
	protected int childrenNumber;
	protected int incomeAmount;
	protected int liabilitiesAmount;

	protected String city;

	public double getInterestRate() {
		return interestRate;
	}

	public int getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}

	public int getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public int getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(int incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public int getLiabilitiesAmount() {
		return liabilitiesAmount;
	}

	public void setLiabilitiesAmount(int liabilitiesAmount) {
		this.liabilitiesAmount = liabilitiesAmount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
