package prob3;

public abstract interface MuteQuack extends QuackBehavior {

	@Override
	public default void quack() {
		System.out.println("\tcannot quack");
	}

}
