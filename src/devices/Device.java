package devices;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Device implements Observer, Runnable {

	private IntegerProperty deviceId;
	private IntegerProperty carId;
	private IntegerProperty state;
	private IntegerProperty power;
	/**
	 * device type 
	 */
	private StringProperty deviceType;
/**
 * create a specified device, set power to 100
 * @param deviceId id of device
 * @param carId id of car
 */
	public Device(int deviceId, int carId) {
		this.deviceId=new SimpleIntegerProperty(deviceId);
		this.carId = new SimpleIntegerProperty(carId);
		this.state = new SimpleIntegerProperty(0);
		this.power = new SimpleIntegerProperty(100);
		this.deviceType = new SimpleStringProperty("Device");
		System.out.println(this.toString());
	}
	/**
	 *  Device information, separated from constructor 
	 */
	@Override
	public String toString() {
		return "Car " + carId + " Device " + deviceId + " created";
	}
	/**
	 * get device id property
	 * @return integer property device id
	 */
    public IntegerProperty deviceIdProperty() {
        return deviceId;
    }
	public int getDeviceId() {
		return deviceId.get();
	}
	/**
	 * set id property
	 * @param int id
	 */
	public void setDeviceId(int deviceId) {
		this.deviceId.set(deviceId);
	}
	/**
	 * use property.get() get power
	 * @return int power
	 */
	public int getPower() {
		return power.get();
	}

	public void setPower(int power) {
		this.power.set(power);
	}
	/**
	 * use property.get() get state
	 * @return int state
	 */
	public int getState() {
		System.out.println("device " + deviceId + " state = " + state);
		return this.state.get();
	}
	/**
	 * use property.set() set power
	 * @param newState 
	 */
	public void setState(int newState) {
		this.state.set(newState);
		System.out.println("Car " + carId + "  Device " + deviceId + " new state = " + state);
	}
	/**
	 * use property.get() get car id
	 * @return int car id
	 */
	public int getCarId() {
		return carId.get();
	}
	public void notifySensor(int state) {
		System.out.println("!!!!");
	}
	/**
	 * set device type property
	 * @param type
	 */
	public void setDeviceType(String type) {
		this.deviceType.set(type); 
	}
	/**
	 * 
	 * @return device type
	 */
	public String getDeviceType() {
		return deviceType.get();
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		String[] splitData = arg.toString().split("&");
		int carID = Integer.parseInt(splitData[0]);
		int deviceId = Integer.parseInt(splitData[1]);
		int state = Integer.parseInt(splitData[2]);
		if (this.carId.get() == carID && this.deviceId.get() == deviceId) {
			setState(state);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int preState;
		while (true) {
			preState = this.state.get();
			while (preState == this.state.get())
				;
			preState = this.state.get();
			notifySensor(this.state.get());
		}
	}
}
