package devices;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeviceFactoryTest {
    final static int speedSensor = 1; //speed sensor id
    final static int lidarSensor = 2; //lidar sensor id
    final static int positionSensor = 3; //position sensor id
    final static int accelerometer = 4;//accelerometer id 
    final static int gyroscope = 5; //gyroscope id
    
	DeviceFactory dFct = new DeviceFactory();


	@Test
	public void testCreateSpeedSensor() {
		Device ss = dFct.createDevice(speedSensor,2, 0);
		assertEquals(300, ss.getPower());
	}
	@Test
	public void testCreateLidarSensor() {
		Device ls = dFct.createDevice(lidarSensor,3, 0);
		assertEquals(300, ls.getPower());
	}
	@Test
	public void testCreatePositionSensor() {
		Device ps = dFct.createDevice(positionSensor,4, 1);
		assertEquals(500, ps.getPower());
	}	
	@Test
	public void testCreateAccelerometer() {
		Device acc = dFct.createDevice(accelerometer,5, 1);
		assertEquals(1200, acc.getPower());
	}
	@Test
	public void testCreateGyroscope() {
		Device gyr = dFct.createDevice(gyroscope,10, 0);
		assertEquals(150, gyr.getPower());
	}
	@Test
	public void testCreateOtherDevice() {
		Device dvc = dFct.createDevice(10,10, 0);
		assertEquals(100, dvc.getPower());
	}	
}
