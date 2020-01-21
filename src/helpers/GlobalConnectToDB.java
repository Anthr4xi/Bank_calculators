package helpers;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class GlobalConnectToDB {
	 
	    public Connection connect() {
	        String url = "jdbc:sqlite:D:\\Programavimas\\Eclipse 2019-06\\BankCalculators\\BankCalculatorDB\\ExchangeRates.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
}
