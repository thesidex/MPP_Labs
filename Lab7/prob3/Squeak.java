package prob3;

public abstract interface Squeak extends QuackBehavior {

	@Override
	public default void quack() {
		System.out.println("\tsqueaking");
	}
}
