package view;


import devices.Device;
import functionality.CreateProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainApp;

public class PropertyEditDialogController {

    @FXML
    private TextField propertyNameField;

    @FXML
    private TextField propertyValueField;
    private Stage dialogStage;
    private boolean okClicked = false;
	//reference to main app
	private MainApp mainApp;
	private Device device;
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
     * @return
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
    }



    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	System.out.println("ok");
        if (isInputValid()) {
            String cid = Integer.toString(device.getCarId());
            String did = Integer.toString(device.getDeviceId());
            String pName = propertyNameField.getText();
            String pValue = propertyValueField.getText();
            String cmd = cid + "," + did + "," + pName + "," + pValue;
            CreateProperty.getInstance().sendCommand(cmd);
            okClicked = true;
            dialogStage.close();
        }
    }
	private boolean isInputValid() {
		 String errorMessage = "";
	        if (propertyNameField.getText() == null || propertyNameField.getText().length() == 0) {
	            errorMessage += "Please specify property name!\n"; 
	        }else if (propertyValueField.getText() == null || propertyValueField.getText().length() == 0) {
	            errorMessage += "Please specify property value!\n"; 
	        }else if( propertyNameField.getText().length()>10) {
	        	errorMessage += "property name should less than 10 characters!\n"; 
	        }else if( propertyValueField.getText().length()>10) {
	        	errorMessage += "property value should less than 10 characters\n";
	        }
	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Invalid Input");
	            alert.setHeaderText("Invalid New Property");
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
     * set device to add property
     * @param selectedDevice
     */
	public void setDevice(Device selectedDevice) {
		this.device = selectedDevice;
		
	}
    
}
