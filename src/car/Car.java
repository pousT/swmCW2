package car;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import devices.Device;
import devices.DeviceFactory;
/**
 * This class is implemented to represent a connected car
 * 
 */
public class Car {
	/**
	 * use factory create device
	 */
    private DeviceFactory deviceFactory; // use device factory to create different devices
	private IntegerProperty carId;
	/**
	 * changed from array list to observable list
	 */
	private ObservableList<Device> devices;
	/**
	 * initialize a car, create empty device observable list
	 * @param carId e.g. 00, 01
	 * @param factory device factory instance, used to create new device
	 */
	public Car(int carId, DeviceFactory factory) {
		this.carId = new SimpleIntegerProperty(carId);
		this.setDevices( FXCollections.observableArrayList());
		this.deviceFactory = factory;
//		System.out.println("car " + carId + " created");
	}
/**
 * This method use factory to create new device and add it to car	
 * @param deviceId device id
 * @param state device state
 * 
 */
	public void addNewDevice(int deviceId, int state){
		Device device = deviceFactory.createDevice(deviceId,getCarId(), state); // create device use factoty
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
	 * @param carId car id
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
    /**
     * 
     * @return observable list of device
     */
	public ObservableList<Device> getDevices() {
		return devices;
	}
	/**
	 * 
	 * @param devices observable list of device
	 */
	public void setDevices(ObservableList<Device> devices) {
		this.devices = devices;
	}

}