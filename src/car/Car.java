package car;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import devices.Device;
import devices.DeviceFactory;

public class Car {
    private DeviceFactory deviceFactory; // use device factory to create different devices
	private IntegerProperty carId;
	public ArrayList<Device> devices;
	/**
	 * 
	 * @param carId e.g. 00, 01
	 * @param factory device factory instance, used to create new device
	 */
	public Car(int carId, DeviceFactory factory) {
		this.carId = new SimpleIntegerProperty(carId);
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
		Device device = deviceFactory.createDevice(deviceId,getCarId()); // create device use factoty
		new Thread(device).start();
		devices.add(device);
		
	}
	/**
	 * use get method of property to get int car id
	 * @return car id
	 */
	public int getCarId() {
		return carId.get();
	}
	/**
	 * use set method of property to set car id 
	 * @param carId
	 */
	public void setCarId(int carId) {
		this.carId.set(carId);
	}
	/**
	 * get car id property
	 * @return integer property car id
	 */
    public IntegerProperty carIdProperty() {
        return carId;
    }
	public ArrayList<Device> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}

}