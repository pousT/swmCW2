package devices;

public class PositionSensor extends Device {
	/**
	 * Create position sensor, set power to 500
	 * @param deviceId id of device
	 * @param carId id of car
	 */
	public PositionSensor(int deviceId, int carId, int state) {
		super(deviceId, carId, state);
		this.setPower(500);
		setDeviceType("Position Sensor");
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " position sensor created";
	}
}
