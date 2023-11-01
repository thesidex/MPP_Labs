package prob3;

public class Cylinder {
	private Circle circle;
	private double height;
	private double volume;
	
	public Cylinder() {
		circle = new Circle(1.0);
		height = 1.0;
		volume = circle.getArea() * height;
	}

	public Cylinder(double radius) {
		circle = new Circle(radius);	
		this.height = 1.0;
		volume = circle.getArea() * height;
	}
	
	public Cylinder(double radius, double height)
	{
		circle = new Circle(radius);
		this.height = height;
		volume = circle.getArea() * height;	
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getVolume()
	{
		return volume;
	}
}
