
import java.util.ArrayList;

import devices.Device;
import devices.DeviceFactory;

public class Car {
    final static int d1Value = 300; //speed sensor
    final static int d2Value = 300; //lidar sensor
    final static int d3Value = 500; //position sensor
    final static int d4Value = 1200;//accelerometer
    final static int d5Value = 150; //gyroscope
    final static int defaultValue = 100;       // This values are used to set power for specified devices.
    private DeviceFactory deviceFactory; // use device factory to create different devices
	private int carId;
	public ArrayList<Device> devices;
	
	public Car(int carId) {
		this.setCarId(carId);
		this.setDevices(new ArrayList<Device>());
//		System.out.println("car " + carId + " created");
	}
/**
 * This method use factory to create new device and add it to car	
 * @param deviceId device id
 * 
 */
	public void addNewDevice(int deviceId){
		Device device = deviceFactory.createDevice(deviceId,carId); // create device use factoty
		new Thread(device).start();
		devices.add(device);
		
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public ArrayList<Device> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}

}