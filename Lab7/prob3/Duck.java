package prob3;

public abstract class Duck implements FlyBehavior, QuackBehavior {
	
	abstract public void display();
	
	public void swim() {
		System.out.println("\tswimming");
	};
	
	public Duck() {}
}
