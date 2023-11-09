package prob3;

public abstract interface FlyBehavior {
	
	default void fly() {
		System.out.println("\tfly with wings");
	};
}
