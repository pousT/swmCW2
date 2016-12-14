package view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import main.MainApp;
import car.Car;
import devices.Device;
import functionality.ChangeState;
import functionality.CreateDevice;
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
    @FXML
    private Button switchButton;
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
    			BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("../img/off.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    	        Background background = new Background(backgroundImage);
    			switchButton.setBackground(background);
    			switchButton.setText("");
    		}
    		else {
    			stateLabel.setText("ON");
    			BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("../img/on.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    	        Background background = new Background(backgroundImage);
    			switchButton.setBackground(background);
    			switchButton.setText("");
    		}
    		// Fill the labels with info from the device object.
    		deviceIdLabel.setText(Integer.toString(device.getDeviceId()));
    		carIdLabel.setText(Integer.toString(device.getCarId()));
    		powerLabel.setText(Integer.toString(device.getPower()));
    		deviceTypeLabel.setText(device.getDeviceType());
    		
    	} else {
    		// device is null, remove all the text.
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
    	int selectedIndex = deviceTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex < 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device.");

            alert.showAndWait();    		
    	}
    	else {
	        String cid = carIdLabel.getText();
	        String did = deviceIdLabel.getText();
	        String cmd = cid + "," + did;
	        DeleteDevice.getInstance().sendCommand(cmd);    		
    	}

    }
    /**
     * Called when the user clicks on the switch button.
     */
    @FXML
    private void handleChangeState() {
    	int selectedIndex = deviceTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex < 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device.");

            alert.showAndWait();    		
    	}
    	else {
	        String cid = carIdLabel.getText();
	        String did = deviceIdLabel.getText();
	        String curState = stateLabel.getText();
	        String state = null;
	        if(curState == "OFF") {
	        	state = "1"; // if off, switch to ON	        	
	        } else if (curState == "ON") {
	        	state = "0";
	        }
	        String cmd = cid + "," + did + "," + state;
	        ChangeState.getInstance().sendCommand(cmd); 
	        showDeviceDetails(deviceTable.getSelectionModel().getSelectedItem());
    	}

    }
    /**
     * Called when the user clicks on the new button.
     */
    @FXML
    private void handleNewDevice() {
		boolean okClicked = mainApp.showDevicenNewDialog();
		if (okClicked) {
			
		}
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
