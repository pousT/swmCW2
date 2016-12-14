package devices;

public class SpeedSensor extends Device {
/**
 * Create speed sensor, set power to 300
 * @param deviceId id of device
 * @param carId id of car
 * @param state 
 */
	public SpeedSensor(int deviceId, int carId, int state) {
		super(deviceId, carId, state);
		setPower(300);
		setDeviceType("Speed Sensor");		
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " speed sensor created";
	}
}
