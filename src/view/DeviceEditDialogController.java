package view;
import car.Car;

import devices.*;
import functionality.CreateDevice;
import functionality.DeleteDevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.MainApp;
import javafx.scene.Group;
public class DeviceEditDialogController {

    @FXML
    private ComboBox<Car> carIdBox;
    @FXML
    private TextField deviceIdField;
    @FXML
    private ToggleGroup state;
    @FXML
    private RadioButton offButton;
    @FXML
    private RadioButton onButton;
    @FXML
    private TextField deviceTypeField;
    private Stage dialogStage;
    private boolean okClicked = false;
	//reference to main app
	private MainApp mainApp;
	private ObservableList<Car> cars;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return true if clicked ok, false otherwise
     */
    public boolean isOkClicked() {
        return okClicked;
    }
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp 
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.cars = mainApp.getcarData();
    }
    /**
     * set car id list in combo box
     */
    public void setCarIdBox(){
        carIdBox.setItems(mainApp.getcarData());
        carIdBox.setConverter(new StringConverter<Car>() {

            @Override
            public String toString(Car car) {
               return Integer.toString(car.getCarId());
            }

            @Override
            public Car fromString(String string) {
               return null;
            }
        });    	
    }
    /**
     * set off and on value for state radio button
     */
    public void setRadioButton() {
    	offButton.setUserData("0");
    	onButton.setUserData("1");
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	System.out.println("ok");
        if (isInputValid()) {
            String cid = Integer.toString(carIdBox.getValue().getCarId());
            String did = deviceIdField.getText();
            String dstate = (String) state.getSelectedToggle().getUserData();
            String cmd = cid + "," + did + "," + dstate;
            CreateDevice.getInstance().sendCommand(cmd);
            okClicked = true;
            dialogStage.close();
        }
    }
	private boolean isInputValid() {
		 String errorMessage = "";
	        if (carIdBox.getValue() == null) {
	            errorMessage += "Please select a car!\n"; 
	        }else if (deviceIdField.getText() == null || deviceIdField.getText().length() == 0){
	        	 errorMessage += "Please set device id!\n"; 
	        } else {
	            // try to parse the postal code into an int.
	            try {
	                Integer.parseInt(deviceIdField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Invalid device id (must be an integer)!\n"; 
	            }
		
	        }
	        if(errorMessage.length() == 0) {
	        	int did = Integer.parseInt(deviceIdField.getText());
	        	if(searchDevice(carIdBox.getValue(), did) != null) { // device already exists
	        		errorMessage += "device id already exists!\n"; 
	        	}
	        }
	        if(errorMessage.length() == 0) {
	        	return true;        	
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Invalid Input");
	            alert.setHeaderText("Invalid New Device");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();  	        	
	        	return false;
	        }
	        
	}
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
	/**
	 * check whether a device exists
	 * @param car
	 * @param did
	 * @return
	 */
    private Device searchDevice(Car car, int did) {

		ObservableList<Device> devices = car.getDevices();
		for(int j = 0; j < devices.size(); j++) {
			if(devices.get(j).getDeviceId() == did) {
				return devices.get(j);
			}
		}	
    	return null;
    }
}
