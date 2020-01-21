package windowsMenu;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LeasingLoanCalculatorWindow {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LeasingLoanCalculatorWindow window = new LeasingLoanCalculatorWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(565, 419);
		shell.setText("Leasing loan calculator");
		
		Label lblPurchaseValue = new Label(shell, SWT.NONE);
		lblPurchaseValue.setBounds(10, 31, 119, 15);
		lblPurchaseValue.setText("Purchase value:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(172, 28, 103, 21);
		
		Label lblEur = new Label(shell, SWT.NONE);
		lblEur.setBounds(327, 31, 55, 15);
		lblEur.setText("EUR");
		
		Label lblInterestRate = new Label(shell, SWT.NONE);
		lblInterestRate.setBounds(10, 77, 103, 15);
		lblInterestRate.setText("Interest rate:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(172, 74, 103, 21);
		
		Label lblPerYear = new Label(shell, SWT.NONE);
		lblPerYear.setBounds(327, 77, 55, 15);
		lblPerYear.setText("% per year");
		
		Label lblPaymentTerm = new Label(shell, SWT.NONE);
		lblPaymentTerm.setBounds(10, 129, 103, 15);
		lblPaymentTerm.setText("Payment term");
		
		Combo comboTermYears = new Combo(shell, SWT.NONE);
		comboTermYears.setBounds(172, 126, 91, 23);
		comboTermYears.add("1 year", 0);
		comboTermYears.add("2 years", 1);
		comboTermYears.add("3 years", 2);
		comboTermYears.add("4 years", 3);
		comboTermYears.add("5 years", 4);
		comboTermYears.select(4);
		
		Label lblFirstInstallment = new Label(shell, SWT.NONE);
		lblFirstInstallment.setBounds(10, 202, 135, 15);
		lblFirstInstallment.setText("First installment:");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(172, 199, 103, 23);
		
		Combo comboInstallment = new Combo(shell, SWT.NONE);
		comboInstallment.setBounds(296, 199, 63, 23);
		comboInstallment.add("%", 0);
		comboInstallment.add("EUR", 1);
		comboInstallment.select(0);
		
		Label lblTypeOfLeasing = new Label(shell, SWT.NONE);
		lblTypeOfLeasing.setBounds(10, 274, 135, 15);
		lblTypeOfLeasing.setText("Type of leasing:");
		
		Combo comboTypeLeasing = new Combo(shell, SWT.NONE);
		comboTypeLeasing.setBounds(172, 271, 103, 23);
		comboTypeLeasing.add("Financial", 0);
		comboTypeLeasing.add("Operating", 1);
		comboTypeLeasing.select(0);
		
		Button btnMainMenu = new Button(shell, SWT.NONE);
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				MainMenu menuWindow= new MainMenu();
				menuWindow.open();
			}
		});
		btnMainMenu.setBounds(410, 345, 129, 25);
		btnMainMenu.setText("Main Menu");

		

	}
}
