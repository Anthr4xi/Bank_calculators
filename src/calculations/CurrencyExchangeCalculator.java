package calculations;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import helpers.CurrencyUpdateDB;
import helpers.GlobalConnectToDB;
import helpers.NumberRounder;
import model.Currency;



public class CurrencyExchangeCalculator {
	
	GlobalConnectToDB connectDb = new GlobalConnectToDB();
    ArrayList<model.Currency> currencyList = new ArrayList<model.Currency>();
    NumberRounder round = new NumberRounder();
    
    public ArrayList<model.Currency> getCurrencies() throws IOException{
    	
    	CurrencyUpdateDB updateCurrenciesDB = new CurrencyUpdateDB();
    	updateCurrenciesDB.connectToLBAndUpdateCurrency();
    	
    	currencyList.clear();
        String sql = "SELECT DISTINCT a.CurrencyCode, b.CurrencyName, a.CurrencyRate, a.CurrencyDate " + 
        			 "FROM Currency_rates a, Currency_names b " +
        			 "WHERE a.CurrencyDate = (SELECT MAX(CurrencyDate) FROM Currency_rates c WHERE c.CurrencyCode = a.CurrencyCode " +
        			 "AND a.CurrencyCode = b.CurrencyCode) " +
        			 "ORDER BY b.CurrencyName ASC";
        
        try (Connection conn = connectDb.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

        	currencyList.add(new Currency("EUR", "Euro", 1.00, "2000-00-00"));
        	while (rs.next()) {
                currencyList.add(new model.Currency(rs.getString("CurrencyCode"), 
                								  rs.getString("CurrencyName"),
                								  rs.getDouble("CurrencyRate"),
                								  rs.getString("CurrencyDate")));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		return currencyList;
    }
    
    public double calculateRate(String type, String currencyNameHave, String currencyNameNeed, double have) {
    	double margin = 0;
    	if (!currencyNameHave.equals(currencyNameNeed)) {
    	if (type.equals("Cash")) margin = 0.003;
    	if (type.equals("Transfer")) margin = 0.002;
    	}
    	
    	double haveRate = getRateValue(currencyNameHave);
    	double needRate = getRateValue(currencyNameNeed);
    	double need = (have / haveRate * needRate) - (have / haveRate * needRate) * margin;
    	return round.numberRounder(need);
    }
        
    public double getRateValue (String currencyName) {
    	double currencyRate = 0;
    	for (Currency currency : currencyList) {
			if (currency.getName().equalsIgnoreCase(currencyName)) currencyRate = currency.getRate();
		}
    	return currencyRate;
    }
   


}
