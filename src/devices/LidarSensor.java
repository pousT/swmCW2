package devices;

public class LidarSensor extends Device {
	/**
	 * Create lidar sensor, set power to 300
	 * @param deviceId id of device
	 * @param carId id of car
	 */
	public LidarSensor(int deviceId, int carId) {
		super(deviceId, carId);
		setPower(300);
		// TODO Auto-generated constructor stub
	}

}
