package prob3;

//this is the product
abstract public class Vehicle {
	String name;

	public String getName() {
		return name;
	}

	public void startEngine() {
		System.out.println("Starting Engine for " + name);
	}
}
