package windowsMenu;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import calculations.MortgageLoanCalculatorAnnuity;
import calculations.MortgageLoanCalculatorLinear;
import helpers.MortgageLoanCalculatorHelper;

public class MortgageLoanCalculatorWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MortgageLoanCalculatorWindow window = new MortgageLoanCalculatorWindow();
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
		shell.setLayout(null);
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
		shell.setSize(600, 500);
		shell.setText("Mortgage Loan amount calculator");

		Label lblGrownUps = new Label(shell, SWT.NONE);
		lblGrownUps.setBounds(285, 75, 29, 15);

		Slider sliderGrownUps = new Slider(shell, SWT.BORDER);
		sliderGrownUps.setThumb(1);

		sliderGrownUps.setPageIncrement(10);
		sliderGrownUps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				lblGrownUps.setText((Integer.toString(sliderGrownUps.getSelection() / 10)));
			}

			@Override
			public void mouseDown(MouseEvent e) {

			}
		});

		sliderGrownUps.setBounds(320, 73, 216, 17);
		sliderGrownUps.setMinimum(10);
		sliderGrownUps.setMaximum(41);
		sliderGrownUps.setIncrement(10);
		sliderGrownUps.setSelection(10);

		lblGrownUps.setText(Integer.toString(sliderGrownUps.getSelection() / 10));

		Label lblChildren = new Label(shell, SWT.NONE);
		lblChildren.setBounds(285, 137, 29, 15);

		Slider sliderChildren = new Slider(shell, SWT.NONE);
		sliderChildren.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				lblChildren.setText(Integer.toString(sliderChildren.getSelection() / 10));
			}
		});
		sliderChildren.setBounds(320, 137, 216, 17);
		sliderChildren.setMinimum(0);
		sliderChildren.setMaximum(110);
		sliderChildren.setIncrement(10);
		sliderChildren.setSelection(0);

		lblChildren.setText(Integer.toString(sliderChildren.getSelection() / 10));

		Text textIncome = new Text(shell, SWT.BORDER);
		Slider sliderIncome = new Slider(shell, SWT.NONE);

		textIncome.setBounds(10, 202, 120, 21);
		textIncome.setText(Integer.toString(sliderIncome.getSelection()));
		textIncome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					sliderIncome.setSelection(Integer.parseInt(textIncome.getText()));
					if (Integer.parseInt(textIncome.getText()) > 10000)
						textIncome.setText("10000");
				} catch (Exception e2) {
					textIncome.setText("0");
				}
			}
		});
		sliderIncome.setBounds(320, 202, 216, 17);
		sliderIncome.setMinimum(0);
		sliderIncome.setMaximum(10010);
		sliderIncome.setSelection(0);
		sliderIncome.setIncrement(25);
		sliderIncome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				textIncome.setText(Integer.toString(sliderIncome.getSelection()));
			}
		});

		Text textObligations = new Text(shell, SWT.BORDER);
		Slider sliderObligations = new Slider(shell, SWT.NONE);

		sliderObligations.setMaximum(10010);
		sliderObligations.setMinimum(0);
		sliderObligations.setSelection(0);
		sliderObligations.setIncrement(25);
		sliderObligations.setBounds(320, 264, 216, 17);
		sliderObligations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				textObligations.setText(Integer.toString(sliderObligations.getSelection()));
			}
		});

		textObligations.setBounds(10, 264, 120, 21);
		textObligations.setText(Integer.toString(sliderObligations.getSelection()));
		textObligations.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {
					sliderObligations.setSelection(Integer.parseInt(textObligations.getText()));
					if (Integer.parseInt(textObligations.getText()) > 10000)
						textObligations.setText("10000");
				} catch (Exception e2) {
					textObligations.setText("0");
				}
			}
		});

		Combo comboCity = new Combo(shell, SWT.READ_ONLY);
		comboCity.setBounds(10, 338, 120, 23);
		comboCity.add("Vilnius", 0);
		comboCity.add("Kaunas", 1);
		comboCity.add("Klaipeda", 2);
		comboCity.add("Other", 3);
		comboCity.select(0);

		Label lblNumberOfGrownups = new Label(shell, SWT.NONE);
		lblNumberOfGrownups.setBounds(10, 73, 248, 15);
		lblNumberOfGrownups.setText("Number of grown-ups in the family");

		Label lblNumberOfChildren = new Label(shell, SWT.NONE);
		lblNumberOfChildren.setBounds(10, 139, 248, 15);
		lblNumberOfChildren.setText("Number of children (dependents) in the family");

		Label lblFamilyIncomeAfter = new Label(shell, SWT.NONE);
		lblFamilyIncomeAfter.setBounds(10, 181, 248, 15);
		lblFamilyIncomeAfter.setText("Family income after taxes");

		Label lblTheFamilysFinancial = new Label(shell, SWT.NONE);
		lblTheFamilysFinancial.setBounds(10, 243, 248, 15);
		lblTheFamilysFinancial.setText("The family\u2019s financial obligations");

		Label lblPleaseSelectYour = new Label(shell, SWT.NONE);
		lblPleaseSelectYour.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblPleaseSelectYour.setBounds(10, 317, 170, 15);
		lblPleaseSelectYour.setText("City of the family’s residence");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(340, 94, 20, 15);
		label.setText("1");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(398, 96, 29, 15);
		label_1.setText("2");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(456, 96, 20, 15);
		label_2.setText("3");

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(503, 96, 20, 15);
		label_3.setText("4");

		Button btnBackToMain = new Button(shell, SWT.NONE);
		btnBackToMain.setBounds(420, 10, 142, 25);
		btnBackToMain.setText("Main Menu");

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(427, 160, 20, 15);
		label_4.setText("5");

		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("1");
		label_5.setBounds(340, 161, 20, 15);

		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("10");
		label_6.setBounds(503, 160, 20, 15);

		Button btnCalculate = new Button(shell, SWT.NONE);
		btnCalculate.setBounds(340, 336, 120, 25);
		btnCalculate.setText("Calculate");

		Button btnPrintSchedule = new Button(shell, SWT.NONE);
		btnPrintSchedule.setBounds(340, 390, 120, 25);
		btnPrintSchedule.setText("Print Loan Schedule");
		btnPrintSchedule.setVisible(false);

		Label lblMaxLoanAmountText = new Label(shell, SWT.NONE);
		Label lblMaxLoanAmountAmount = new Label(shell, SWT.NONE);
		Button btnAnnuity = new Button(shell, SWT.RADIO);
		Button btnLinear = new Button(shell, SWT.RADIO);
		Label lblInterestRateText = new Label(shell, SWT.NONE);
		Label lblSpace = new Label(shell, SWT.NONE);
		Label lblChangeParametersText = new Label(shell, SWT.NONE);
		Slider sliderMaxLoanAmount = new Slider(shell, SWT.NONE);
		Label lblLoanAmountText = new Label(shell, SWT.NONE);
		Label lblLoanAmount = new Label(shell, SWT.NONE);
		Slider sliderMonthlyInstallment = new Slider(shell, SWT.NONE);
		Label lblMonthlyInstallmentText = new Label(shell, SWT.NONE);
		Label lblMonthlyInstallment = new Label(shell, SWT.NONE);
		Slider sliderMaturityYears = new Slider(shell, SWT.NONE);
		Label lblMaturityYearsText = new Label(shell, SWT.NONE);
		Label lblMaturityYearsAmount = new Label(shell, SWT.NONE);

		MortgageLoanCalculatorHelper helper = new MortgageLoanCalculatorHelper();
		MortgageLoanCalculatorAnnuity calcAnnuity = new MortgageLoanCalculatorAnnuity();
		MortgageLoanCalculatorLinear calcLinear = new MortgageLoanCalculatorLinear();

		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				btnPrintSchedule.setVisible(true);
				helper.setAdultNumber(Integer.parseInt(lblGrownUps.getText()));
				helper.setChildrenNumber(Integer.parseInt(lblChildren.getText()));
				helper.setIncomeAmount(Integer.parseInt(textIncome.getText()));
				helper.setLiabilitiesAmount(Integer.parseInt(textObligations.getText()));
				helper.setCity(comboCity.getText());

				int maxLoanCalculatedAnnuity = calcAnnuity.maxLoanAmountAnnuity(helper.getIncomeAmount(),
						helper.getLiabilitiesAmount(), helper.getAdultNumber(), helper.getChildrenNumber(),
						helper.getCity());
				int maxLoanCalculatedLinear = calcLinear.maxLoanAmountLinear(helper.getIncomeAmount(),
						helper.getLiabilitiesAmount(), helper.getAdultNumber(), helper.getChildrenNumber(),
						helper.getCity());
				double maxInstallment = helper.netIncome(helper.getIncomeAmount(), helper.getLiabilitiesAmount(),
						helper.getAdultNumber(), helper.getChildrenNumber(), helper.getCity());

				shell.setSize(600, 700);

				btnAnnuity.setBounds(10, 400, 90, 16);
				btnAnnuity.setText("Annuity");
				btnAnnuity.setSelection(true);

				btnLinear.setBounds(100, 400, 90, 16);
				btnLinear.setText("Linear");
				btnLinear.setSelection(false);

				lblMaxLoanAmountText.setBounds(10, 440, 350, 15);
				lblMaxLoanAmountText.setText("Maximum loan amount, which you can borrow is:");
				lblMaxLoanAmountText.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
				lblMaxLoanAmountAmount.setBounds(370, 440, 150, 15);
				lblMaxLoanAmountAmount.setText(Integer.toString(maxLoanCalculatedAnnuity));
				lblMaxLoanAmountAmount.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));

				lblInterestRateText.setBounds(10, 480, 350, 15);
				lblInterestRateText.setText("Calculation has been based on annual interest rate of 2.9%");
				lblInterestRateText.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));

				lblSpace.setBounds(10, 500, 550, 15);
				lblSpace.setText(
						"------------------------------------------------------------------------------------------------------------------------------------");

				lblChangeParametersText.setBounds(10, 520, 350, 15);
				lblChangeParametersText.setText("Also you can borrow a smaller amount:");
				lblChangeParametersText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));

				sliderMaxLoanAmount.setMaximum(maxLoanCalculatedAnnuity + 10);
				sliderMaxLoanAmount.setMinimum(3000);
				sliderMaxLoanAmount.setSelection(maxLoanCalculatedAnnuity + 10);
				sliderMaxLoanAmount.setIncrement(100);
				sliderMaxLoanAmount.setBounds(320, 550, 216, 17);

				lblLoanAmountText.setBounds(10, 550, 130, 15);
				lblLoanAmountText.setText("Loan amount:");

				lblLoanAmount.setBounds(150, 550, 350, 15);
				lblLoanAmount.setText(Integer.toString(maxLoanCalculatedAnnuity));

				sliderMonthlyInstallment.setMaximum((int) maxInstallment + 10);
				sliderMonthlyInstallment.setMinimum(1);
				sliderMonthlyInstallment.setSelection((int) maxInstallment + 10);
				sliderMonthlyInstallment.setIncrement(20);
				sliderMonthlyInstallment.setBounds(320, 580, 216, 17);

				lblMonthlyInstallmentText.setBounds(10, 580, 130, 15);
				lblMonthlyInstallmentText.setText("Monthly installment:");

				lblMonthlyInstallment.setBounds(150, 580, 350, 15);
				lblMonthlyInstallment.setText(Integer.toString((int) maxInstallment));

				sliderMaturityYears.setMaximum(370);
				sliderMaturityYears.setMinimum(12);
				sliderMaturityYears.setSelection(370);
				sliderMaturityYears.setIncrement(12);
				sliderMaturityYears.setBounds(320, 610, 216, 17);

				lblMaturityYearsText.setBounds(10, 610, 130, 15);
				lblMaturityYearsText.setText("Maturity years:");

				lblMaturityYearsAmount.setBounds(150, 610, 350, 15);
				lblMaturityYearsAmount.setText("30");

				btnLinear.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {

						lblMaxLoanAmountAmount.setText(Integer.toString(maxLoanCalculatedLinear));
						lblLoanAmount.setText(Integer.toString(maxLoanCalculatedLinear));
						sliderMaxLoanAmount.setMaximum(maxLoanCalculatedLinear);
						sliderMaxLoanAmount.setSelection(maxLoanCalculatedLinear);
						sliderMonthlyInstallment.setSelection((int) maxInstallment);
						lblMonthlyInstallment.setText(Integer.toString((int) maxInstallment));
						sliderMaturityYears.setSelection(sliderMaturityYears.getMaximum());
						lblMaturityYearsAmount.setText("30");

					}
				});

				btnAnnuity.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {

						lblMaxLoanAmountAmount.setText(Integer.toString(maxLoanCalculatedAnnuity));
						lblLoanAmount.setText(Integer.toString(maxLoanCalculatedAnnuity));
						sliderMaxLoanAmount.setMaximum(maxLoanCalculatedAnnuity);
						sliderMaxLoanAmount.setSelection(maxLoanCalculatedAnnuity);
						sliderMonthlyInstallment.setSelection((int) maxInstallment);
						lblMonthlyInstallment.setText(Integer.toString((int) maxInstallment));
						sliderMaturityYears.setSelection(sliderMaturityYears.getMaximum());
						lblMaturityYearsAmount.setText("30");
					}
				});

				sliderMaxLoanAmount.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						int calculation;
						if (btnAnnuity.getSelection())
							calculation = calcAnnuity.loanAmountToPayPerMonthAnnuity(sliderMaxLoanAmount.getSelection(),
									sliderMaturityYears.getSelection()) + 1;
						else
							calculation = calcLinear.loanAmountToPayPerMonthLinear(sliderMaxLoanAmount.getSelection(),
									sliderMaturityYears.getSelection()) + 1;

						lblLoanAmount.setText(Integer.toString(sliderMaxLoanAmount.getSelection()));

						if (calculation > sliderMonthlyInstallment.getMaximum()) {

							sliderMonthlyInstallment.setSelection(sliderMonthlyInstallment.getMaximum() - 10);
							lblMonthlyInstallment.setText(Integer.toString(sliderMonthlyInstallment.getMaximum() - 10));
							lblMaturityYearsAmount.setText(Integer.toString(helper
									.periodCalculation(btnAnnuity.getSelection(), sliderMaxLoanAmount.getSelection())));
							sliderMaturityYears.setSelection((helper.periodCalculation(btnAnnuity.getSelection(),
									sliderMaxLoanAmount.getSelection())) * 12);

						} else {
							sliderMonthlyInstallment.setSelection(calculation);
							lblMonthlyInstallment.setText(Integer.toString(calculation));
						}
					}
				});

				sliderMonthlyInstallment.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						int calculation;
						if (btnAnnuity.getSelection())
							calculation = calcAnnuity.loanAmountAnnuity(sliderMonthlyInstallment.getSelection(),
									sliderMaturityYears.getSelection());
						else
							calculation = calcLinear.loanAmountLinear(sliderMonthlyInstallment.getSelection(),
									sliderMaturityYears.getSelection());

						lblMonthlyInstallment.setText(Integer.toString(sliderMonthlyInstallment.getSelection()));
						sliderMaxLoanAmount.setSelection(calculation);
						lblLoanAmount.setText(Integer.toString(calculation));
					}
				});

				sliderMaturityYears.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						int calculation;
						if (btnAnnuity.getSelection())
							calculation = calcAnnuity.loanAmountAnnuity(sliderMonthlyInstallment.getSelection(),
									sliderMaturityYears.getSelection());
						else
							calculation = calcLinear.loanAmountLinear(sliderMonthlyInstallment.getSelection(),
									sliderMaturityYears.getSelection());

						lblMaturityYearsAmount.setText(Integer.toString(sliderMaturityYears.getSelection() / 12));
						sliderMaxLoanAmount.setSelection(calculation);
						lblLoanAmount.setText(Integer.toString(calculation));
					}
				});

				btnPrintSchedule.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						MortgageLoanScheduleWindow scheduleWindow = new MortgageLoanScheduleWindow();
						scheduleWindow.open(btnAnnuity.getSelection(), sliderMaxLoanAmount.getSelection(), sliderMaturityYears.getSelection());
					}
				});
			}
		});

		btnBackToMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.close();
				MainMenu menuWindow = new MainMenu();
				menuWindow.open();

			}
		});

	}
}
