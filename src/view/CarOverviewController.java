package view;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.MainApp;
import car.Car;

import devices.Device;
import functionality.DeleteDevice;
public class CarOverviewController {
	@FXML
    private TableView<Car> carTable;
	@FXML
	private TableColumn<Car, Integer> carIdColumn;
	@FXML
	private TableView<Device> deviceTable;
	@FXML
	private TableColumn<Device, Integer> deviceIdColumn;	
    @FXML
    private Label deviceTypeLabel;
    @FXML
    private Label deviceIdLabel;
    @FXML
    private Label carIdLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label powerLabel;

	//reference to main app
	private MainApp mainApp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */	
	public CarOverviewController() {
		// TODO Auto-generated constructor stub
	}
    @FXML
    private void initialize() {
        // Initialize the car table with the one column.
        carIdColumn.setCellValueFactory(cellData -> cellData.getValue().carIdProperty().asObject());
		carTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showCarDetails(newValue));
    }
    /**
     * shows devices in the car in device table
     * If the specified car is null, device table is empty
     * 
     * @param car the car or null
     */   
    private void showCarDetails(Car car) {
    	if(car != null) {
    		deviceTable.setItems(car.getDevices());
            deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty().asObject());
    		deviceTable.getSelectionModel().selectedItemProperty().addListener(
    				(observable, oldValue, newValue) -> showDeviceDetails(newValue));
        }
	}
    /**
     * Show details of a selected device
     * @param device
     */
    private void showDeviceDetails(Device device) {
    	if (device != null) {
    		if(device.getState() == 0) {
    			stateLabel.setText("OFF");
    		}
    		else {
    			stateLabel.setText("ON");
    		}
    		// Fill the labels with info from the person object.
    		deviceIdLabel.setText(Integer.toString(device.getDeviceId()));
    		carIdLabel.setText(Integer.toString(device.getCarId()));
    		powerLabel.setText(Integer.toString(device.getPower()));
    		deviceTypeLabel.setText(device.getDeviceType());
    		
    	} else {
    		// Person is null, remove all the text.
    		deviceIdLabel.setText("");
    		carIdLabel.setText("");
    		stateLabel.setText("");
    		powerLabel.setText("");
    		deviceTypeLabel.setText("");
    	}
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteDevice() {
        String cid = carIdLabel.getText();
        String did = deviceIdLabel.getText();
        String cmd = cid + "," + did;
        DeleteDevice.getInstance().sendCommand(cmd);
    }
    /**
     * Called when the user clicks on the new button.
     */
    @FXML
    private void handleNewDevice() {
        String cid = carIdLabel.getText();
        String did = deviceIdLabel.getText();
        String cmd = cid + "," + did;
        DeleteDevice.getInstance().sendCommand(cmd);
    }
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        carTable.setItems(mainApp.getcarData());        
    }
}
