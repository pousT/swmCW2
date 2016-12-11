package devices;

public class Accelerometer extends Device {
/**
 * create accelerometer with device id and car id, set power to 1200
 * @param deviceId id of device 
 * @param carId id of car 
 */
	public Accelerometer(int deviceId, int carId) {
		super(deviceId, carId);
		setPower(1200);
		setDeviceType("Accelerometer");
	}
@Override
public String toString() {
	return "Car "+ this.getCarId() +  " Device " + this.getDeviceId() + " accelerometer created";
}
}
