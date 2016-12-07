package car;

import java.util.ArrayList;

import devices.Device;
import devices.DeviceFactory;

public class Car {
    private DeviceFactory deviceFactory; // use device factory to create different devices
	private int carId;
	public ArrayList<Device> devices;
	/**
	 * 
	 * @param carId e.g. 00, 01
	 * @param factory device factory instance, used to create new device
	 */
	public Car(int carId, DeviceFactory factory) {
		this.setCarId(carId);
		this.setDevices(new ArrayList<Device>());
		this.deviceFactory = factory;
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