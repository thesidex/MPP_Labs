package prob1.partB;
import java.util.Objects;

public class Employee {
	private String name;
	private int salary;

	public Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "(" + name + ", " + salary + ")";
	}
	
	@Override
	public boolean equals(Object ob) {
		if (ob == null) return false;
		if (!(ob instanceof Employee)) return false;
		Employee p = (Employee) ob;
		return p.name.equals(name) && p.salary == salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
	}
}
