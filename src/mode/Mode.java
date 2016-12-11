package mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.ConnectionDB;
import functionality.ModeDevice;
import simulator.Simulator;

/**
 * This class is used to implement the auto mode.
 */
public class Mode extends AbstractMode{

	private static final String ON = "1";
	private static final String OFF = "0";
	private HashMap<String , String[]> hashMap;
	private HashMap<String , String> choosemode;
	private static final String AUTO = "/Users/Documents/workspace/Auto_config.csv";
	private String mode; 
	List<String> cdid = new ArrayList<String>();
	List<String> dvalue = new ArrayList<String>();
	
	Simulator simulator;
	ConnectionDB connect = ConnectionDB.getInstance();
	ModeDevice modedevice = ModeDevice.getInstance();
	
	public void putmode() 
	{
		choosemode.put("Auto", AUTO);
	}
	
	public Mode(Simulator sim, String modename) {
		// TODO Auto-generated constructor stub	
		simulator = sim;
		hashMap = new HashMap<String, String[]>();
		choosemode = new HashMap<String, String>();
		mode = modename;
		putmode();
	}
	
	public String choosemode()
	{
		return choosemode.get(mode);
	}
	
	/** 
	 * see AbstractMode.java 
	 *
	 */
	@Override
	public void initial() {

		String[] datas = null; 
		File myFile=new File(choosemode());
		String str = null;
		try{
			BufferedReader in = new BufferedReader(new FileReader(myFile));
        	str = in.readLine();
        	in.close();
		} catch (IOException e) {
            e.getStackTrace();
        }
		
		datas = str.split(",");
		String[] temperature = {datas[0],datas[1],datas[2]};
		String[] consumption = {datas[6],datas[7],datas[8]};
		String[] queue = new String[datas.length-9];
		int counter = 0;
		for(int i=9; i<datas.length; i++) {
//			simulator.changeState(queue[counter]+"&1");
			queue[counter] = datas[i];
			counter++;
		}
		hashMap.put("temperature", temperature);
		hashMap.put("consumption",consumption);
		hashMap.put("queue", queue);
	}
	/** 
	 * see AbstractMode.java 
	 *
	 */
	@Override
	public int getAllConsumption() {
		cdid = connect.getID();
		int sumConsumption = 0;
		for(int i=0; i<cdid.size(); i++) {
			if(connect.checkState(cdid.get(i)).equals("true")){			
				System.out.println(" simulator.getPower(rdid.get(i)) is : "+simulator.getPower(cdid.get(i)));			
				sumConsumption += simulator.getPower(cdid.get(i));
			}
		}	
		return sumConsumption;
	}
	/** 
	 * see AbstractMode.java 
	 *
	 */
	@Override
	public void checkTemperature(int currentTemperature) {
		String[] temperature = hashMap.get("temperature");	// 3 args
		System.out.println(temperature[0]);
		int upper = Integer.parseInt(temperature[0]);
		int lower = Integer.parseInt(temperature[1]);
		if(currentTemperature > upper && connect.checkDID("1").size()!=0) {
			connect.updateDIDState("1", OFF);
			for(int i=0; i<connect.checkDID("1").size();i++) {
				simulator.changeState(connect.checkDID("1").get(i));
			}
		}else if(currentTemperature < lower && connect.checkDID("1").size()!=0) {
			connect.updateDIDState("1", ON);
			for(int i=0; i<connect.checkDID("1").size();i++) {
				simulator.changeState(connect.checkDID("1").get(i));
			}
		}else {
			System.out.println("There isn't such humidity device, Please check again!");
		}
	}
	/** 
	 * see AbstractMode.java 
	 *
	 */
	@Override
	public void checkConsumption() {
		String[] consumption = hashMap.get("consumption");
		String[] device = hashMap.get("queue");
		int upper = Integer.parseInt(consumption[0]);
		int lower = Integer.parseInt(consumption[1]);

		if(getAllConsumption() > upper) {						
			for(int i=0; i<device.length; i++) {							
				if(connect.checkID(device[i])) {
					if(connect.checkState(device[i]).equals("true")) {								
						connect.updateState(device[i], OFF);
						simulator.changeState(device[i]+"&"+OFF);
					}
					if(getAllConsumption() <upper) {
						break;
					}
				} else {
					System.out.println("There isn't such a "+device[i]+" device, Please check again!");
				}	
			}
		}else if(getAllConsumption() < lower) {
			for(int i=0; i<device.length; i++) {
				if(connect.checkID(device[i])) {
					if(connect.checkState(device[i]).equals("false")) {
						connect.updateState(device[i], ON);
						simulator.changeState(device[i]+"&"+ON);
					}
					if(getAllConsumption()>lower) {
						break;
					}
				} else {
					System.out.println("There isn't such a "+device[i]+" device, Please check again!");
				}	
			}
		}
		
		System.out.println("The currentsumConsumption in time final is: "+getAllConsumption());
	}
	
	/** 
	 * this method is used to update the history database 
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
//		ConnectionDB.insertHistroy(updateHistroy);
		return updateHistory;
	}
	
	/**
	 * this method is used to update the currentDevice database 
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

	/**
	 * This method is used to implement the TimerTask class method
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("\n\n-------------------Start-----------------------");	
		int counter = modedevice.getCounter();
		checkTemperature(25);
		checkTemperature(simulator.getTemperature());
		checkConsumption();
		updateMYSQLCurrentDevice(counter);
		if(counter%20==0) {
			connect.insertHistory(updateHistory());
		}
		System.out.println("counterHour: "+counter+"!!!!!!!!!");
		System.out.println("---------------------End-----------------------\n\n");

		modedevice.setCounter(++counter);	
	}
}	
