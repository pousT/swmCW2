package devices;

public interface IDeviceFactory {
	public Device createDevice(int deviceId, int carId);
}
