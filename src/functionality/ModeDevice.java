package functionality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;

import db.ConnectionDB;
import mode.ManualMode;
import mode.Mode;
import simulator.Simulator;

/**
 * this class is used to determine mode 
 * 
 */
public class ModeDevice extends Functionality{
	private int counter = 1;
	static Simulator sim;
	ConnectionDB connect = ConnectionDB.getInstance();
	private static final String CHANGE = "./InputCommand/ChangeState.csv";
	public static ModeDevice instance;
	
	synchronized public static ModeDevice getInstance() {
		if(instance == null){
			instance = new ModeDevice(sim);
		}   	
    	return instance;
	}
	
	public ModeDevice(Simulator simulator) {
		sim = simulator;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
/**
 * this sendCommand() is used to read from the Mode.csv file, ignore the first line, get the mode, call the determine the mode
 * 
 */
	public void sendCommand() {
		
		try {
			BufferedReader readFromFile = new BufferedReader(new FileReader("./InputCommand/Mode.csv"));
			readFromFile.readLine(); 
			String mode = readFromFile.readLine();
			determineMode(mode);
			readFromFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
/**
 * this method determine mode is used to determine mode
 * 
 */
	public void determineMode(String mode) {
		switch (mode) {
		    case "Auto":
		    	Mode switchmode = new Mode(sim, mode);     		  
		    	switchmode.initial();
		    	connect.turnOnAll();
		    	connect.ChangeMaxConnection();
		    	Timer timer1 = new Timer();
		    	timer1.schedule(switchmode, 1000,10000);
		    	break;
		    case "Manual":
		    	ManualMode manual = new ManualMode(sim);     		
		    	manual.initial();
		    	if(new File(CHANGE).length() != 0) {
		    		manual.ChangeState(CHANGE);
		    	}
		    	connect.ChangeMaxConnection();
		    	Timer timer2 = new Timer();
		    	timer2.schedule(manual, 1000,10000);
		    	break;
		    }
	    }
		
}
