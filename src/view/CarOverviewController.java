package view;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Main.MainApp;
import car.Car;
public class CarOverviewController {
	@FXML
    private TableView<Car> carTable;
	@FXML
	private TableColumn<Car, Integer> carIdColumn;
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
