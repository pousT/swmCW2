package view;
import car.Car;

import devices.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.Group;
public class DeviceEditDialogController {

    @FXML
    private TextField carIdField;
    @FXML
    private TextField deviceIdField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    
    private Stage dialogStage;
    private Device device;
    private boolean okClicked = false;
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
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setDevice(Device device) {
        this.device = device;

        carIdField.setText(Integer.toString(device.getCarId()));
        deviceIdField.setText(Integer.toString(device.getDeviceId()));
        stateField.
    }
	public DeviceEditDialogController() {
		// TODO Auto-generated constructor stub
	}

}
