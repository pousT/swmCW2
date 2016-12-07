package devices;

public class Gyroscope extends Device {
/**
 * Set power to 150
 * @param deviceId id of device
 * @param carId id of car
 */
	public Gyroscope(int deviceId, int carId) {
		super(deviceId, carId);
		setPower(150);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " gyroscope created";
	}
}
