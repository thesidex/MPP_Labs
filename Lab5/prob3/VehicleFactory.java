package prob3;

//this is the factory
public class VehicleFactory {
	public static Vehicle startVehicle(String type) {
		Vehicle ve = null;
		if (type.equals("bus"))
			ve = new Bus();
		else if (type.equals("truck"))
			ve = new Truck();
		else if (type.equals("car"))
			ve = new Car();
		else if (type.equals("electric car"))
			ve = new ElectricCar();
		return ve;
	}
}
