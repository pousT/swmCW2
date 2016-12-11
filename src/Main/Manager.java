package Main;

import java.util.HashMap;

import functionality.ChangeState;
import functionality.CreateDevice;
import functionality.DeleteDevice;
import functionality.Functionality;
import functionality.ModeDevice;
import simulator.Simulator;

/**
 * this class is used to manage the device.
 * 
 */
public class Manager {
	
	Simulator simulator;
	HashMap<String, Object> hashmap = new HashMap<String, Object>();

	/**
	 * construct a constructor with hashmap to put the key=filename, value=object(mode)
	 * 
	 */
	public Manager(Simulator sim) {
		// TODO Auto-generated constructor stub
		simulator = sim;		
		hashmap.put("Create.csv", CreateDevice.getInstance());
		hashmap.put("Delete.csv", DeleteDevice.getInstance());
		hashmap.put("ChangeState.csv", ChangeState.getInstance());
		hashmap.put("Mode.csv", ModeDevice.getInstance());
	}
	
	/**
	 * this method is used to get the object's sendCommand() method
	 * 
	 */
	public void SwitchCsvObject(String filename) {		
		((Functionality) hashmap.get(filename)).sendCommand();
	}	   
}
