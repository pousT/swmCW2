package devices;

public class LidarSensor extends Device {
	/**
	 * Create lidar sensor, set power to 300
	 * @param deviceId id of device
	 * @param carId id of car
	 */
	public LidarSensor(int deviceId, int carId, int state) {
		super(deviceId, carId, state);
		setPower(300);
		setDeviceType("Lidar Sensor");
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " lidar sensor created";
	}
}
