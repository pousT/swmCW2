package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class is used to connect with database which using JDBC.
 * 
 */
public class ConnectionDB {  

	private static final String DBDRIVER="com.mysql.jdbc.Driver"; 
	private static final String DBURL="jdbc:mysql://localhost:3306/testdb";  
    private static final String DBUSER="root";  
    private static final String DBPASSWORD="passadmin"; 
    private final static int max = (int) Math.pow(2, 63);
    private final static String max_connection = "SET GLOBAL max_connections = "+max ;
	
    static Connection conn = null ;  
        
    static Statement st;    
     
    private static ConnectionDB instance;
    
    /**
     * this method implement the singleton pattern.
     */
    synchronized public static ConnectionDB getInstance() {
		if(instance == null){
			instance = new ConnectionDB();
		}   	
    	return instance;
	}
    
    /**
     * this method is used to change the max connection to database.
     */
    public void ChangeMaxConnection() {
    	conn = getConnection();
    	try {
			st = conn.createStatement();
	        st.executeQuery(max_connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * this method is used to connect with currentDevice table.
     * @return Connection object
     */
    public Connection getConnection() {    
        Connection con = null;     
        try {    
            Class.forName(DBDRIVER);   
                    
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);    

        } catch (Exception e) {    
            System.out.println("Connection failed!" + e.getMessage());    
        }    
        return con;    
    }
    
    /**
     * this method is used to check DID' state from currentDevice table.
     * @return int
     */
    public int checkDIDState(String did) {
  	
    	conn = getConnection();
    	int number = 0;
    	
    	try{
        	String ssql = "SELECT STATE FROM CurrentDevice WHERE (DID = '"+did+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
            	String state = rs.getString("STATE");
            	System.out.println(state);
            	
            	if(state.equals("1")) {
            		number++;
            	}
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
    	
    	return number;
    }
    
    /**
     * this method is used to check CID&DID's state from currentDevice table.
     * @return String
     */
    public String checkState(String cdid) {
    	
    	conn = getConnection();
    	String resultcheck = null;
    	
    	String[] cdids = cdid.split("&");
    	try{
        	String ssql = "SELECT STATE FROM CurrentDevice WHERE (CID = '"+cdids[0]+"') AND (DID = '"+cdids[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);    
            
            while(rs.next()) {
            	String state = rs.getString("STATE");
            	
            	if(!state.equals("0")) {
            		resultcheck = "true";
            	}else {
            		resultcheck = "false";
            	}
            	
            }
        	      	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
    	return resultcheck;
    }
    
    /**
     * this method is used to change all the device's state on.
     * @return List<String>
     */
    public List<String> getStateOn() {
	
    	conn = getConnection(); 
        List<String> result = new ArrayList<String>();
    	
        try{
        	String ssql = "SELECT CID, DID FROM CurrentDevice WHERE (STATE = 0)";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                String string= null;
                string = rs.getString("CID")+"&"+rs.getString("DID");
            	result.add(string);
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
        return result;
    }
    
    public List<String> checkDID(String did) {
    	conn = getConnection();
    	List<String> didsList = new ArrayList<String>();
        try{
        	String ssql = "SELECT CID, DID, STATE FROM CurrentDevice WHERE (DID = '"+did+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                String string= null;
                string = rs.getString("CID")+"&"+rs.getString("DID")+"&"+rs.getString("STATE");
                didsList.add(string);
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
        return didsList;
    }
    
    /**
     * this method is used to check DID's state, and if the state is on, then return true, else return false.
     * @return boolean
     */
    public boolean checkID(String id) {
    	conn = getConnection();
    	boolean check = false;
//    	String result = null;
    	int counter = 0;
    	String ssql[] = id.split("&");
    	String sql = "SELECT * FROM CurrentDevice WHERE (CID = '"+ssql[0]+"') AND (DID = '"+ssql[1]+"')";
    	try {
			st = conn.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        
	        while(rs.next()) {
	        	counter = rs.getInt(1);
	        }
	        if(counter == 0) {
	        	check = false;
	        }else {
	        	check = true;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return check;
    }
    
    /**
     * this method is used to get the CID&DID from table and store them into a list.
     * @return List<String>
     * @throws SQLException 
     */
    public List<String> getID() {
    	
    	conn = getConnection(); 
        List<String> result = new ArrayList<String>();
    	
        try{
        	String ssql = "SELECT CID, DID FROM currentdevice";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                String string= null;
                string = rs.getString("CID")+"&"+rs.getString("DID");
            	result.add(string);
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
        return result;
    }
    
    public List<String> getState(String id) {
    	
    	conn = getConnection(); 
    	String sql[] = id.split("&");
        List<String> result = new ArrayList<String>();
    	
        try{
        	String ssql = "SELECT STATE FROM CurrentDevice WHERE CID = ('"+sql[0]+"') AND DID = ('"+sql[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                String string= null;
                string = rs.getString("STATE");
            	result.add(string);
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
        return result;
    }

    /**
     * this method is used to get the VALUE from table and return as a String.
     * @return String
     */
    public String getValue(String cdid) {
    	
    	conn = getConnection(); 
        String result = null;
        String[] id = cdid.split("&");
    	
        try{
        	String ssql = "SELECT DVALUE FROM CurrentDevice WHERE (CID='"+id[0]+"') AND (DID='"+id[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                result = rs.getString("DVALUE");
            }
        	
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}
        return result;
    }
    
    /**
     * this method is used to insert the sum consumption into History table.
     * @return void
     */
    public void insertHistory(long sum) {    	
    	conn = getConnection();        
        try {          		
        	String sql = "INSERT INTO History(SDVALUE)"    
	        			+ " VALUES ('"+sum+"')";
	                    
	        st = conn.createStatement();
            st.executeUpdate(sql); 	   
	                        
            st.close();
            conn.close(); 
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}      
    }
    
    /**
     * this method is used to turn all the device's state to on.
     * 
     */
    public void turnOnAll() {
    	conn = getConnection();
    	String sql = "UPDATE CurrentDevice SET STATE = '1'";
    	
        try {
        	st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void insertCidDid(String data,String state) {
    	conn = getConnection(); 
        String linedata[] = data.split(",");
        Date date = new Date();
        
        try {
        	String ssql = "SELECT * FROM CurrentDevice WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;
            	
            while(rs.next()) {
            	counter = rs.getInt(1);
            	System.out.println(rs.getString("DID")+"!111111111");
            }
                   
           	if(counter == 0){ // no data           	
	            String sql = "INSERT INTO CurrentDevice(CID, DID, STATE, TIMEV)"    
	                        + " VALUES ('"+linedata[0]+"','"+linedata[1]+"','"+state+"','"+date+"')";   
	            System.out.println("sql="+sql);  	                    
	            st = conn.createStatement();						  
	            int count = st.executeUpdate(sql);    	                        
	            System.out.println("insert into CurrentDevice " + count + " numbers of data");   
	                        
	            st.close();
	            conn.close(); 
	            
            }
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}     
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * this method is used to insert into the currentDevice new Entry.
     */
    public void insert(String data){  
    	
        conn = getConnection(); 
        String linedata[] = data.split(",");
        Date date = new Date();
        
        try {
        	String ssql = "SELECT * FROM CurrentDevice WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;
            	
            while(rs.next()) {
            	counter = rs.getInt(1);
            	System.out.println(rs.getString("DID")+"!111111111");
            }
                   
           	if(counter == 0){ // no data 
            	
	            String sql = "INSERT INTO CurrentDevice(CID, DID, DVALUE, STATE, TIMEV)"    
	                        + " VALUES ('"+linedata[0]+"','"+linedata[1]+"','"+linedata[2]+"','"+linedata[3]+"','"+date+"')";   
	            System.out.println("sql="+sql);  
	                    
	            st = conn.createStatement();
						  
	            int count = st.executeUpdate(sql);
	                        
	            System.out.println("insert into CurrentDevice " + count + " numbers of data");   
	                        
	            st.close();
	            conn.close(); 
	            
            }
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}  
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * this method is used to delete from the currentDevice table.
     */
    public void delete(String data) {
    	
    	conn = getConnection(); 
        String linedata[] = data.split(",");
        
        try {
				
	        	String sql = "DELETE FROM CurrentDevice"
	        				 +" WHERE (CID="+linedata[0]+")"+"AND (DID="+linedata[1]+")";
	        
	        	st = conn.createStatement();
			
	        	int count = st.executeUpdate(sql);
	        	
	        	System.out.println("delete from CurrentDevice " + count + " numbers of data");   
	            
	            st.close();
	            conn.close();
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    /**
     * this method is used to update the currentDevice table VALUE.
     */
    public void updateValue(String data, long sum) 
    {
    	
    	conn = getConnection(); 
        String linedata[] = data.split("&");
        
        try {
				
	        	String sql = "UPDATE CurrentDevice SET DVALUE = '"+sum+"' WHERE (CID= '"+linedata[0]+"')"+"AND (DID= '"+linedata[1]+"')";
	        
	        	st = conn.createStatement();
			
	        	int count = st.executeUpdate(sql);
	        	
	        	System.out.println("update from CurrentDevice " + count + " numbers of data");   
	            
	            st.close();
	            conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    /**
     * this method is used to update the currentDevice table DSTATE.
     */
    public void updateState(String data, String state) 
    {
    	
    	conn = getConnection(); 
        String linedata[] = data.split("&");
        
        try {
        	String ssql = "SELECT * FROM CurrentDevice WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;        	
            while(rs.next()) {
            	counter = rs.getInt(1);
            }        	
			if(counter !=0 ) { // if have data
				
	        	String sql = "UPDATE CurrentDevice SET STATE = '"+state+"' WHERE (CID='"+linedata[0]+"')"+"AND (DID='"+linedata[1]+"')";
	        
	        	st = conn.createStatement();
			
	        	int count = st.executeUpdate(sql);
	        	
	        	System.out.println("update from CurrentDevice " + count + " numbers of data");   
	            
	            st.close();
	            conn.close();
			}else {
				insertCidDid(data, state);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    /**
     * this method is used to insert into currentDevice table the attribute of HOUR_INT.
     */
    public void insertHourValue(String data, int hour, long sum){  
    	
        conn = getConnection(); 
        String linedata[] = data.split(",");

        Date date = new Date();
        
        try {
        	String ssql = "SELECT HOUR_"+hour+" FROM CurrentDevice WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;
            	
            while(rs.next()) {
            	counter = rs.getInt(1);
            }
                   
           	if(counter == 0){ // no data 
            		
	            String sql = "INSERT INTO CurrentDevice(CID, DID, STATE, DVALUE,TIMEV,HOUR_1,HOUR_2,HOUR_3,HOUR_4,HOUR_5,HOUR_6,HOUR_7,HOUR_8,HOUR_9,HOUR_10,HOUR_11,HOUR_12)"    
	                        + " VALUES ('"+linedata[0]+"','"+linedata[1]+"','"+linedata[2]+"','"+linedata[3]+"','"+date+"','"+"')";   
	            System.out.println("sql="+sql);  
	                    
	            st = conn.createStatement();
						  
	            int count = st.executeUpdate(sql);    
	                        
	            System.out.println("insert into CurrentDevice " + count + " numbers of data");   
	                        
	            st.close();
	            conn.close(); 
	            
            }
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
		}  
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * this method is used to update the currentDevice table the attribute of HOUR_INT.
     */
    public void updateHourValue(String data, int hour, long sum)
    {
    	
    	conn = getConnection(); 
        String linedata[] = data.split("&");
        
        try {				
	        String sql = "UPDATE CurrentDevice SET HOUR_"+hour+"='"+sum+"' WHERE (CID='"+linedata[0]+"')"+"AND (DID='"+linedata[1]+"')";	     
	      	st = conn.createStatement();			
	       	int count = st.executeUpdate(sql);    	
	        System.out.println("update from CurrentDevice " + count + " numbers of data");   
	            
	        st.close();
	        conn.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    /**
     * this method is used to update the currentDevice table the attribute of DVALUE.
     */
    public void updateCurrentDeviceDValue(String data) {
    	conn = getConnection(); 
        String linedata[] = data.split("&");
        int counter = 0;
    	try {
    		for(int i=0; i<12; i++) {
    			String ssql = "SELECT HOUR_"+(i+1)+" FROM CurrentDevice WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1]+"')";
				st = conn.createStatement();
				ResultSet rs = st.executeQuery(ssql);
				while(rs.next()){
					counter+=Integer.parseInt(
							rs.getString("HOUR_"+(i+1)));
				}				
    		}
            updateValue(data, counter);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * this method is used to update the currentDevice table the attribute of DID'State.
     */
    public void updateDIDState(String did, String state) 
    {	
    	conn = getConnection(); 
        try {
        	String ssql = "SELECT CID, DID FROM CurrentDevice WHERE (DID='"+did+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;          	
            while(rs.next()) {
            	counter = rs.getInt(1);  
            	
            	System.out.println(counter);
            } 
       			if(counter !=0 ) { // if have data				
	        	String sql = "UPDATE CurrentDevice SET STATE = '"+state+"' WHERE (DID= '"+did+"')";	        
	        	st = conn.createStatement();			
	        	int count = st.executeUpdate(sql);	        	
	        	System.out.println("update from CurrentDevice " + count + " numbers of data");   
	            
	            st.close();
	            conn.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    /**
     * Insert a property for a specified car device into Property table
     * @param data
     */
	public void insertProperty(String data) {
        conn = getConnection(); 
        String linedata[] = data.split(",");
        try {
        	String ssql = "SELECT * FROM Property WHERE (CID='"+linedata[0]+"') AND (DID='"+linedata[1] +"') AND (PNAME='"+ linedata[2]+"')";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);
            int counter = 0;
            	
            while(rs.next()) {
            	counter = rs.getInt(1);
            	System.out.println(rs.getString("DID")+"!111111111");
            }
                   
           	if(counter == 0){ // no data 
            	
	            String sql = "INSERT INTO Property(CID, DID, PNAME, PVALUE)"    
	                        + " VALUES ('"+linedata[0]+"','"+linedata[1]+"','"+linedata[2]+"','"+linedata[3]+"')";   
	            System.out.println("sql="+sql);  
	                    
	            st = conn.createStatement();
						  
	            int count = st.executeUpdate(sql);
	                        
	            System.out.println("insert into Property " + count + " numbers of data");   
	                        
	            st.close();
	            conn.close(); 
           	}
        } catch(SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public List<String> getProperty() {
    	conn = getConnection(); 
        List<String> result = new ArrayList<String>();
    	
        try{
        	String ssql = "SELECT * FROM Property";
        	st = conn.createStatement();
            ResultSet rs = st.executeQuery(ssql);         

            while(rs.next()) {
                String string= null;
                string = rs.getString("CID")+"&"+rs.getString("DID")+ "&" +rs.getString("PNAME") + "&" +rs.getString("PVALUE");
            	result.add(string);
            }
        	
        } catch (SQLException e) {
        	
        	e.printStackTrace();
		}
        return result;
	}

	public void deletePropertyById(String cmd) {
    	conn = getConnection(); 
        String linedata[] = cmd.split(",");
        
        try {
				
	        	String sql = "DELETE FROM Property"
	        				 +" WHERE (CID="+linedata[0]+")"+"AND (DID="+linedata[1]+")";
	        
	        	st = conn.createStatement();
			
	        	int count = st.executeUpdate(sql);
	        	
	        	System.out.println("delete from Property " + count + " numbers of data");   
	            
	            st.close();
	            conn.close();
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
