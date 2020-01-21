package windowsMenu;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import calculations.MortgageLoanSchedulePrinter;
import model.LoanScheduleRecord;

public class MortgageLoanScheduleWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MortgageLoanScheduleWindow window = new MortgageLoanScheduleWindow();
			window.open(false, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(boolean type, double loanAmount, int periodMonths) {
		Display display = Display.getDefault();
		createContents(type, loanAmount, periodMonths);
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
	protected void createContents(boolean type, double loanAmount, int periodMonths) {
		shell = new Shell();
		shell.setSize(600, 660);
		shell.setText("Mortgage Loan Payment Schedule");

		Table table = new Table(shell, SWT.BORDER);
		table.setBounds(10, 10, 551, 600);

		TableColumn column1 = new TableColumn(table, SWT.LEFT);
		TableColumn column2 = new TableColumn(table, SWT.LEFT);
		TableColumn column3 = new TableColumn(table, SWT.LEFT);
		TableColumn column4 = new TableColumn(table, SWT.LEFT);
		TableColumn column5 = new TableColumn(table, SWT.LEFT);
		column1.setText("Period");
		column2.setText("Monthly Installment");
		column3.setText("Monthly Principle");
		column4.setText("Monthly Interest");
		column5.setText("Remaining loan");

		column1.setWidth(50);
		column2.setWidth(120);
		column3.setWidth(120);
		column4.setWidth(120);
		column5.setWidth(120);
		table.setHeaderVisible(true);

		MortgageLoanSchedulePrinter calc = new MortgageLoanSchedulePrinter();
		ArrayList<LoanScheduleRecord> calculation = null;
				
		if (type) calculation = calc.loanShceduleAnnuity(loanAmount, periodMonths);
		else calculation = calc.loanShceduleLinear(loanAmount, periodMonths);

		for (LoanScheduleRecord payment : calculation) {
			new TableItem(table, SWT.NONE).setText(new String[] { payment.getPeriod(), payment.getInstallmentAmount(),
					payment.getPrinciple(), payment.getInterestAmount(), payment.getLeftLoan() });

		}
	}
}
