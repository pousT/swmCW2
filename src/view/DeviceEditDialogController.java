package view;
import car.Car;

import devices.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    
    private Stage dialogStage;
    private boolean okClicked = false;
	//reference to main app
	private MainApp mainApp;
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
}
