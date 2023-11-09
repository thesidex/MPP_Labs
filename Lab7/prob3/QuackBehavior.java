package prob3;

public interface QuackBehavior {
	default void quack() {
		System.out.println("\tquacking");
	};
}
