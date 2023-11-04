package prob2;

import java.util.ArrayList;
import java.util.List;

public class Main {

	private static double sumAreas = 0;

	public static void main(String[] args) {

		List<Figure> list = new ArrayList<Figure>();

		list.add(new Circle(4));
		list.add(new Triangle(4, 5));
		list.add(new Rectangle(6, 10));

		list.forEach(figure -> {
			sumAreas += figure.computeArea();
		});

		System.out.println(sumAreas);
	}
}
