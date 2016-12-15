package view;

import devices.Device;
import functionality.CreateProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
		// TODO Auto-generated method stub
		return true;
	}
	public void setDevice(Device selectedDevice) {
		this.device = selectedDevice;
		
	}
    
}
