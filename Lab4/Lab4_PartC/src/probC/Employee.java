package probC;

import java.time.LocalDate;

abstract public class Employee {
	private String empId;
	public Employee(String empId) {
		this.setEmpId(empId);
	}
	public void print(int month, int year) {
		LocalDate now = LocalDate.of(year, month, 1);
		System.out.println(calcCompensation(now.getMonthValue(), now.getYear()));
	}
	public Paycheck calcCompensation(int month, int year) {
		double grossPay = calcGrossPay(month, year);
		return new Paycheck(grossPay, Tax.FICA, Tax.State, 
				Tax.Local, Tax.Medicare, Tax.SocialSecurity);
	}

	abstract public double calcGrossPay(int month, int year);
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
