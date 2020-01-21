package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import model.Currency;

public class CurrencyUpdateDB {

	private static ArrayList<Currency> currencyList = new ArrayList<Currency>();
	GlobalConnectToDB connectDb = new GlobalConnectToDB();

	public ArrayList<Currency> connectToLBAndUpdateCurrency() throws IOException {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		
		String url = "https://lb.lt/webservices/fxrates/FxRates.asmx/getFxRates?tp=EU&dt=" + dateFormat.format(cal.getTime());
		String xml = crunchifyGetURLContents(url);

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xml)));

			int sumOfFxRate = (int) countAllFxrateEntriesFromXml(document);
			currencyList.clear();
			currencyList = getAllCcyOfxml(document, sumOfFxRate);

		} catch (Exception e) {

			System.out.println("no connection with URL:" + url);
		}
		return currencyList;
	}

	private ArrayList<Currency> getAllCcyOfxml(Document document, int sumOfFxRate) throws XPathExpressionException {

		for (int i = 1; i <= sumOfFxRate; i++) {

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression exprCode = xpath.compile("/FxRates//FxRate[" + i + "]//CcyAmt[2]//Ccy");
			XPathExpression exprRate = xpath.compile("/FxRates//FxRate[" + i + "]//CcyAmt[2]//Amt");
			XPathExpression exprDate = xpath.compile("/FxRates//FxRate[" + i + "]/Dt");

			String code = exprCode.evaluate(document, XPathConstants.STRING).toString();
			double rate = Double.parseDouble(exprRate.evaluate(document, XPathConstants.STRING).toString());
			String date = exprDate.evaluate(document, XPathConstants.STRING).toString();

			insertIntoSql(code, rate, date);
			currencyList.add(new Currency(code, rate, date));

		}
		return currencyList;
	}

	private void insertIntoSql(String code, double rate, String date) {
		String sql = "INSERT INTO Currency_rates(CurrencyCode,CurrencyRate,CurrencyDate) VALUES(?,?,?)";

		try (Connection conn = connectDb.connect();

				PreparedStatement pstmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement()) {

			ResultSet result = null;
			result = stmt.executeQuery("SELECT 1 FROM Currency_rates WHERE CurrencyCode=" + "'" + code + "'"
					+ " AND CurrencyDate =" + "'" + date + "'");
			if (!result.isBeforeFirst()) {
				pstmt.setString(1, code);
				pstmt.setDouble(2, rate);
				pstmt.setString(3, date);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static double countAllFxrateEntriesFromXml(Document document) throws XPathExpressionException {

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression exprr = xpath.compile("count(//FxRates/FxRate)");

		return (double) exprr.evaluate(document, XPathConstants.NUMBER);

	}

	private static String crunchifyGetURLContents(String myURL) {

		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 10000000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);

					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("no connection with URL:" + myURL);

		}

		return sb.toString();
	}
}
