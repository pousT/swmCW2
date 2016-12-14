package main;


import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import simulator.Simulator;
import view.CarOverviewController;
import view.DeviceEditDialogController;
import car.Car;

import db.ConnectionDB;
import devices.Device;
/**
 * This class is the entrance of GUI
 * @author tangshulan
 *
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Car> carData;
    private Simulator sim;
    private ConnectionDB db;
    /**
     * initialize simulator, database instance and car list
     */
    public MainApp() {
    	sim = Simulator.getInstance();
    	db = ConnectionDB.getInstance();
    	loadRecords();
    	carData = sim.getCars();
    }
    /**
     * Returns the data as an observable list of cars. 
     * 
     */
    public ObservableList<Car> getcarData() {
        return carData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Connected Car App");

        initRootLayout();

        showCarOverview();
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Shows the car overview scene.
     */
    public void showCarOverview() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/CarOverview.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);
            CarOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
	/**
	 * Opens a dialog to add new device for the specified car. If the user
	 * clicks OK, the device is created and true
	 * is returned.
	 * 
	 * @param car the car object to add device 
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showDevicenNewDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/DeviceEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Device");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the car into the controller.
			DeviceEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * This method load car record from database, moved from previous main method in 
	 * FileMonitor class
	 */
    private void loadRecords() {
		List<String> resultID = db.getID();

		if(db.getID().size()!=0) {
			for(int i=0; i<resultID.size(); i++) {
				sim.addDevice(resultID.get(i)+"&"+
												  db.getState(resultID.get(i)).get(0)+"&"+
											      db.getValue(resultID.get(i)));
			}
		}    	
    }

    public static void main(String[] args) {
        launch(args);
    }
}
