package devices;

public interface IDeviceFactory {
	public Device createDevice(String type, int deviceId, int carId);
}
