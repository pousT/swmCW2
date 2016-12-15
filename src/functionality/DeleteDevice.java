package functionality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import db.ConnectionDB;
import simulator.Simulator;

/**
 * this class is used to delete object when user want to delete some devices.
 * 
 */
public class DeleteDevice extends Functionality{
	 private static Simulator simulator; 
	 private static DeleteDevice instance;
	 ConnectionDB connect = ConnectionDB.getInstance();
	/**
	 *   change constructor to private for singleton pattern
	 * @param simulator
	 */
	 private DeleteDevice(Simulator simulator){
		 DeleteDevice.simulator = simulator;
	 }
	 synchronized public static DeleteDevice getInstance() {
		if(instance == null){
			instance = new DeleteDevice(Simulator.getInstance());
		}   	
	    	return instance;
	 }
	   
	   @Override
	public void sendCommand(){
		   BufferedReader reader = null;
	       try {
	           reader = new BufferedReader(new FileReader("./InputCommand/Delete.csv"));
	           String tempString = null;
	           while ((tempString = reader.readLine()) != null) {
	        	   String[] commands = tempString.split(",");
	        	   simulator.removeDevice(commands[0]+"&"+commands[1]);
	        	   connect.delete(tempString);
	           }
	           reader.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } 
	   }
	@Override
	public void sendCommand(String cmd) {
 	   String[] commands = cmd.split(",");
 	   simulator.removeDevice(commands[0]+"&"+commands[1]);
 	   connect.delete(cmd);
 	   connect.deletePropertyById(cmd);
	}
}
