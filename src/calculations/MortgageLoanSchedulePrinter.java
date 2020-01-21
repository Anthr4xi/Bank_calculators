package calculations;

import java.util.ArrayList;

import helpers.MortgageLoanCalculatorHelper;
import helpers.NumberRounder;
import model.LoanScheduleRecord;

public class MortgageLoanSchedulePrinter {

	NumberRounder round = new NumberRounder();
	public ArrayList<LoanScheduleRecord> loanList = new ArrayList<LoanScheduleRecord>();
	MortgageLoanCalculatorHelper helper = new MortgageLoanCalculatorHelper();

	public ArrayList<LoanScheduleRecord> loanShceduleAnnuity(double loanAmount, int periodMonths) {
		loanList.clear();
		double monthlyInstallment = loanAmount * helper.annuityCoeficient(periodMonths);
		for (int i = 1; i <= periodMonths; i++) {
			double interestAmount = loanAmount * 0.029 / 12;
			double principle = monthlyInstallment - interestAmount;
			loanAmount = loanAmount - principle;

			loanList.add(new LoanScheduleRecord(Integer.toString(i),
					Double.toString(round.numberRounder(monthlyInstallment)),
					Double.toString(round.numberRounder(principle)),
					Double.toString(round.numberRounder(interestAmount)),
					Double.toString(round.numberRounder(loanAmount))));
		}
		return loanList;
	}

	public ArrayList<LoanScheduleRecord> loanShceduleLinear(double loanAmount, int periodMonths) {
		loanList.clear();
		double principle = loanAmount / periodMonths;
		for (int i = 1; i <= periodMonths; i++) {
			double interestAmount = loanAmount * 0.029 / 12;
			double monthlyInstallment = principle + interestAmount;
			loanAmount = loanAmount - principle;

			loanList.add(new LoanScheduleRecord(Integer.toString(i),
					Double.toString(round.numberRounder(monthlyInstallment)),
					Double.toString(round.numberRounder(principle)),
					Double.toString(round.numberRounder(interestAmount)),
					Double.toString(round.numberRounder(loanAmount))));
		}
		return loanList;
	}

}
