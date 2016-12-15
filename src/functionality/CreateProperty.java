package functionality;

import db.ConnectionDB;
import simulator.Simulator;
/**
 * This class is used to create new property for a specified car device
 * @author tangshulan
 *
 */
public class CreateProperty extends Functionality {
	private static Simulator simulator;
    ConnectionDB connect = ConnectionDB.getInstance();
    
	private CreateProperty(Simulator simulator) {
		CreateProperty.simulator = simulator;
	}
    private static CreateProperty instance;
    synchronized public static CreateProperty getInstance() {
		if(instance == null){
			instance = new CreateProperty(Simulator.getInstance());
		}   	
	    	return instance;
	}
	@Override
	public void sendCommand() {
		

	}

	@Override
	public void sendCommand(String cmd) {
		String data = null;
		String[] commands = cmd.split(",");
		simulator.addProperty(commands[0]+"&"+commands[1]+"&"+commands[2]+"&"+commands[3]);
    	data = commands[0]+","+commands[1]+","+commands[2]+","+commands[3];
    	connect.insertProperty(data);   		

	}

}
