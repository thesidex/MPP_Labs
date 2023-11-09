package prob3;

public class DecoyDuck extends Duck implements CannotFly, MuteQuack {
	
	public DecoyDuck() {}

	@Override
	public void display() {
		System.out.println("Displaying - DecoyDuck");
	}
	
}
