package windowsMenu;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainMenu {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainMenu window = new MainMenu();
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
		shell.setSize(529, 379);
		shell.setText("Main Menu");
		
		Button btnMortgageLoanAmount = new Button(shell, SWT.NONE);
		btnMortgageLoanAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				MortgageLoanCalculatorWindow mrtgWindow = new MortgageLoanCalculatorWindow();
				mrtgWindow.open();
				
			}
		});
		btnMortgageLoanAmount.setBounds(10, 64, 237, 25);
		btnMortgageLoanAmount.setText("Mortgage loan amount calculator");
		
		Button btnLeasingLoanAmount = new Button(shell, SWT.NONE);
		btnLeasingLoanAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				LeasingLoanCalculatorWindow leasWindow= new LeasingLoanCalculatorWindow();
				leasWindow.open();
			}
		});
		btnLeasingLoanAmount.setBounds(10, 110, 237, 25);
		btnLeasingLoanAmount.setText("Leasing loan amount calculator");
		
		Button btnCurrencyExchangeCalculator = new Button(shell, SWT.NONE);
		btnCurrencyExchangeCalculator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				CurrecnyExchangeCalculatorWindow exchngWindow= new CurrecnyExchangeCalculatorWindow();
				try {
					exchngWindow.open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCurrencyExchangeCalculator.setBounds(10, 160, 237, 25);
		btnCurrencyExchangeCalculator.setText("Currency exchange calculator");
	}
}
