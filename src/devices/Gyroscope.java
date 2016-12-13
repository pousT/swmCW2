package devices;

public class Gyroscope extends Device {
/**
 * Set power to 150
 * @param deviceId id of device
 * @param carId id of car
 */
	public Gyroscope(int deviceId, int carId, int state) {
		super(deviceId, carId, state);
		setPower(150);
		setDeviceType("Gyroscope");
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " gyroscope created";
	}
}
