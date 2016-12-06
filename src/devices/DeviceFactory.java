package devices;

public class DeviceFactory implements IDeviceFactory {
    final static int speedSensor = 1; //speed sensor
    final static int lidarSensor = 2; //lidar sensor
    final static int positionSensor = 3; //position sensor
    final static int accelerometer = 4;//accelerometer
    final static int gyroscope = 5; //gyroscope
    

	/**
	 * Create device based on device id and car id
	 * @author tangshulan
	 * @param deviceId id of device
	 * @param carId id of car
	 * @return 
	 */
	public Device createDevice(int deviceId, int carId) {
		if ( deviceId== 1 ) {
			return new SpeedSensor(deviceId, carId);
		} else if ( deviceId == 2 ) {
			return new LidarSensor(deviceId, carId);
		} else if ( deviceId == 3 ) {
			return new PositionSensor(deviceId, carId);
		} else if (deviceId == 4) {
			return new Accelerometer(deviceId, carId);
		} else if (deviceId == 5) {
			return new Gyroscope(deviceId, carId);
		} else {
			return new Device(deviceId, carId); // other specified device
		}
			
	}

}
