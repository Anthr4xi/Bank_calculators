package windowsMenu;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import java.io.IOException;

import calculations.CurrencyExchangeCalculator;
import model.Currency;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CurrecnyExchangeCalculatorWindow {

	protected Shell shell;
	private Text textHave;
	private Text textNeed;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CurrecnyExchangeCalculatorWindow window = new CurrecnyExchangeCalculatorWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @throws IOException
	 */
	protected void createContents() throws IOException {
		shell = new Shell();
		shell.setSize(740, 400);
		shell.setText("Currency exchange calculator");

		CurrencyExchangeCalculator calculator = new CurrencyExchangeCalculator();
		Label lblChooseRateType = new Label(shell, SWT.NONE);
		Combo comboType = new Combo(shell, SWT.READ_ONLY);
		Combo comboCurrencyHave = new Combo(shell, SWT.READ_ONLY);
		Label lblIHave = new Label(shell, SWT.NONE);
		Combo comboCurrencyNeed = new Combo(shell, SWT.READ_ONLY);
		Label lblINeed = new Label(shell, SWT.NONE);

		lblChooseRateType.setBounds(10, 78, 120, 15);
		lblChooseRateType.setText("Choose rate type:");

		comboType.setBounds(10, 100, 120, 22);
		comboType.add("Cash", 0);
		comboType.add("Transfer", 1);
		comboType.add("LB official rate", 2);
		comboType.select(1);

		lblIHave.setBounds(230, 78, 55, 20);
		lblIHave.setText("I have:");

		textHave = new Text(shell, SWT.BORDER);
		textHave.setText("1.00");
		textHave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
						try {
				textNeed.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
						comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
				if (Double.parseDouble(textHave.getText()) < 0) textHave.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
						comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
			} catch (Exception e2) {
				textHave.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyNeed.getText(),
						comboCurrencyHave.getText(), Double.parseDouble(textNeed.getText())))));
			}
		}});
		
		textHave.setBounds(230, 100, 120, 23);

		lblINeed.setBounds(230, 130, 55, 20);
		lblINeed.setText("I need:");

		textNeed = new Text(shell, SWT.BORDER);
		textNeed.setBounds(230, 150, 120, 22);
		textNeed.setText("1.00");
		textNeed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
				textHave.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyNeed.getText(),
						comboCurrencyHave.getText(), Double.parseDouble(textNeed.getText())))));
				if (Double.parseDouble(textNeed.getText()) < 0) textNeed.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyNeed.getText(),
						comboCurrencyHave.getText(), Double.parseDouble(textNeed.getText())))));
			} catch (Exception e2) {
				textNeed.setText(Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
						comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
			}
		}});

		comboCurrencyHave.setBounds(400, 100, 300, 20);
		for (Currency currencyName : calculator.getCurrencies()) {
			comboCurrencyHave.add(currencyName.getName());
		}
		comboCurrencyHave.select(0);

		comboCurrencyNeed.setBounds(400, 150, 300, 20);
		for (Currency currencyName : calculator.getCurrencies()) {
			comboCurrencyNeed.add(currencyName.getName());
		}
		comboCurrencyNeed.select(0);

		comboType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textNeed.setText(
						Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
								comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
			}
		});
		
		comboCurrencyHave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textNeed.setText(
						Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
								comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
			}
		});

		comboCurrencyNeed.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textNeed.setText(
						Double.toString((calculator.calculateRate(comboType.getText(), comboCurrencyHave.getText(),
								comboCurrencyNeed.getText(), Double.parseDouble(textHave.getText())))));
			}
		});

		Button btnMainMenu = new Button(shell, SWT.NONE);
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				MainMenu menuWindow = new MainMenu();
				menuWindow.open();
			}
		});
		btnMainMenu.setBounds(557, 10, 120, 25);
		btnMainMenu.setText("Main Menu");

	}

}
