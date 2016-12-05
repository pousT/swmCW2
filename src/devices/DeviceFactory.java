package devices;

public class DeviceFactory implements IDeviceFactory {

	@Override
	public Device createDevice(String type, int deviceId, int carId) {
		if ( "lorry".equalsIgnoreCase(type) ) {
			return new Lorry(id);
		} else if ( "ship".equalsIgnoreCase(type) ) {
			return new Ship(id);
		} else if ( "train".equalsIgnoreCase(type) ) {
			return new Train(id);
		}
		
		return null;		
	}

}
