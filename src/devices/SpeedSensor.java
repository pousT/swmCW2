package devices;

public class SpeedSensor extends Device {
/**
 * Create speed sensor, set power to 300
 * @param deviceId id of device
 * @param carId id of car
 */
	public SpeedSensor(int deviceId, int carId) {
		super(deviceId, carId);
		setPower(300);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " speed sensor created";
	}
}
