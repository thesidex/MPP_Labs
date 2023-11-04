package prob3;

//this is the client
public class App {

	public static void main(String[] args) {
		Vehicle ve = VehicleFactory.startVehicle("electric car");
		ve.startEngine();
	}
}
