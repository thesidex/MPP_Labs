package probC;

public class Salaried extends Employee {
	private double monthlySalary;

	public Salaried(String empId, double salary) {
		super(empId);
		monthlySalary = salary;
	}

	@Override
	public double calcGrossPay(int month, int year) {
		return monthlySalary;
	}
}
