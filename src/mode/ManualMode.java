package mode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionDB;
import functionality.ModeDevice;
import simulator.Simulator;

/**
 * this class is used to implement the User mode.
 * 
 */
public class ManualMode extends AbstractMode {

	private static int counterHour;
	Simulator simulator;
	List<String> cdid = new ArrayList<String>();
	ConnectionDB connect = ConnectionDB.getInstance();
	private static final String cpath = "./InputCommand/Create.csv";
	ModeDevice modedevice = ModeDevice.getInstance();
	
	public ManualMode(Simulator sim) {
		// TODO Auto-generated constructor stub
		simulator = sim;
		ChangeState(cpath);
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		counterHour = modedevice.getCounter();
	}
	/**
	 * this method is used to change the state of which user changes
	 * 
	 */
	public void ChangeState(String path) {
		BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String tempString = null;		
            while ((tempString = reader.readLine()) != null) {    	
            	String[] elements = tempString.split(",");
            	connect.updateState(elements[0]+"&"+elements[1], elements[2]); 
            	simulator.changeState(elements[0]+"&"+elements[1]+"&"+elements[2]);
            }   
        }catch(Exception e) {
        	e.printStackTrace();
        }    
	}	
	/**
	 * see AbstractMode,java
	 * 
	 */
	@Override
	public int getAllConsumption() {
		cdid = connect.getID();
		int sumConsumption = 0;
		for(int i=0; i<cdid.size(); i++) {
			if(connect.checkState(cdid.get(i)).equals("true")){
				sumConsumption += simulator.getPower(cdid.get(i));
			}
		}			
		System.out.println("sumConsumption" + sumConsumption);	
		return sumConsumption;
	}	
	/**
	 * this method is used to update the history table. 
	 * 
	 */
	@Override
	public long updateHistory() {
		cdid = connect.getID();
		long updateHistory = 0;
		for(int i=0; i<cdid.size(); i++) {
			if(connect.checkState(cdid.get(i)).equals("true")) {
				updateHistory += Integer.parseInt(connect.getValue(cdid.get(i)));
			}
		}
		return updateHistory;
	}
	
	/**
	 * this method is used to update the currentDevice table. 
	 * 
	 */
	@Override
	public void updateMYSQLCurrentDevice(int counter) {
		cdid = connect.getID();
		for(int i=0; i<cdid.size(); i++) {
			if(connect.checkState(cdid.get(i)).equals("true")) {
				if(counter<=12) {
					connect.updateHourValue(cdid.get(i), counter, simulator.getPower(cdid.get(i)));
				} else {
					connect.updateHourValue(cdid.get(i), counter%12, simulator.getPower(cdid.get(i)));
				} 
			}else {
				if(counter<=12) {
					connect.updateHourValue(cdid.get(i), counter, 0);
				} else {
					connect.updateHourValue(cdid.get(i), counter%12, 0);
				}
			}
			connect.updateCurrentDeviceDValue(cdid.get(i));
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("\n\n-------------------Start-----------------------");
		int counter = modedevice.getCounter();
		updateMYSQLCurrentDevice(counter);
		if(counter>20) {
			connect.insertHistory(updateHistory());
			 counter = 0;
		}
		System.out.println("counter: "+counter+"!!!!!!!!!");
		System.out.println("---------------------End-----------------------\n\n");
		modedevice.setCounter(++counter);
	}
}
