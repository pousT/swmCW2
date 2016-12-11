package functionality;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import db.ConnectionDB;
import simulator.Simulator;

/**
 * this class is used to change the device state 
 * 
 */
public class ChangeState extends Functionality{
		private Simulator simulator;
		ConnectionDB connect = ConnectionDB.getInstance();
	    private static ChangeState instance;
       /**
        * change constructor to private for singleton pattern
        * @param simulator
        */
	   private ChangeState(Simulator simulator){
		   this.simulator = simulator;
	   }
	   /**
	    * get ChangeState instance method  
	    * @return
	    */
	   synchronized public static ChangeState getInstance() {
			if(instance == null){
				instance = new ChangeState(Simulator.getInstance());
			}   	
		    	return instance;
		 }	   
	   @Override
	public void sendCommand(){
		   BufferedReader reader = null;
	       try {
	           reader = new BufferedReader(new FileReader("./InputCommand/ChangeState.csv"));
	           String tempString = null;
	           reader.readLine();
	           while ((tempString = reader.readLine()) != null) {
	        	   String[] commands = tempString.split(",");
	        	   simulator.changeState(commands[0]+"&"+commands[1]+"&"+commands[2]);
	        	   connect.updateState(commands[0]+"&"+commands[1], commands[2]);
	           }
	           reader.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } 
	   }

	@Override
	public void sendCommand(String cmd) {
 	   String[] commands = cmd.split(",");
 	   simulator.changeState(commands[0]+"&"+commands[1]+"&"+commands[2]);
 	   connect.updateState(commands[0]+"&"+commands[1], commands[2]);
		
	}

}
