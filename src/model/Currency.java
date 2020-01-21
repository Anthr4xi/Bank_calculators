package model;

public class Currency {

	private String code;
	private String name;
	private double rate;
	private String date;
	
	public Currency(String code, double rate, String date) {
		this.code = code;
		this.rate = rate;
		this.date = date;
	}
	
	public Currency(String code, String name, double rate, String date) {
		this.code = code;
		this.name = name;
		this.rate = rate;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
