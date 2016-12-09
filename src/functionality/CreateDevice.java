package functionality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import db.ConnectionDB;
import simulator.Simulator;

/**
 * this class is used to create the device
 * 
 */
public class CreateDevice extends Functionality{
	
	private static Simulator simulator;
    ConnectionDB connect = ConnectionDB.getInstance();
    
    
    public CreateDevice(Simulator simulator){
	    CreateDevice.simulator = simulator;
    }
    
    private static CreateDevice instance;
    synchronized public static CreateDevice getInstance() {
		if(instance == null){
			instance = new CreateDevice(simulator);
		}   	
	    	return instance;
	}

    
    @Override
	public void sendCommand(){
    	System.out.println("......");
    	
	    BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./InputCommand/Create.csv"));
            String tempString = null;
            
            while ((tempString = reader.readLine()) != null) {
        	    String data = null;
        	    String[] commands = tempString.split(",");
        	   
        	    for(int i=0; i<commands.length; i++) {
            	    System.out.println(commands[i]);
        	    }
        	    System.out.println(commands.length);
        	    
        	 	simulator.addDevice(commands[0]+"&"+commands[1]+"&"+commands[2]+"&"+commands[3]);
        		int state = simulator.getState(commands[0]+"&"+commands[1]);
            	data = commands[0]+","+commands[1]+","+state+","+"00";
            	connect.insert(data);   	       
            } 			    
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
