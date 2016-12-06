package devices;

public class PositionSensor extends Device {
	/**
	 * Create position sensor, set power to 500
	 * @param deviceId id of device
	 * @param carId id of car
	 */
	public PositionSensor(int deviceId, int carId) {
		super(deviceId, carId);
		this.setPower(500);
		// TODO Auto-generated constructor stub
	}

}
