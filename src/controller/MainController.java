package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import application.MyLogger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CalcOption;
import model.CalcOption.CalcOptionType;
import model.DateConverter;
import model.InstructionReader;
import model.OptionList;
import model.TableDataModel;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This is controller for MainLayout of this application. It contains listeners
 * and methods for layout elements
 * 
 * 
 * Fields marked with FXML annotation are gotten from layout file
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class MainController {

	@FXML
	private Button saveToFile, zeroing, okButton;
	@FXML
	private TextField valueField;
	@FXML
	private Spinner<Integer> rowSpinner, columnSpinner;
	@FXML
	private TextArea resultArea, instructArea;
	@FXML
	private Label statusInfo;
	@FXML
	private TableView<ObservableList<Double>> tableView;
	@FXML
	private ListView<CalcOption> operationsList;
	@FXML
	private DatePicker datePicker;
	@FXML
	private BarChart<String, Integer> tableValueChart;
	@FXML
	private AnchorPane naviLabelsPane;

	/**
	 * List for options to calculate
	 */
	private ObservableList<CalcOption> optionsList;
	/**
	 * Model for table which contains double values
	 */
	private TableDataModel<Double> dataModel;
	/**
	 * sizes of the table
	 */
	private final int TABLE_COLUMNS = 5, TABLE_ROWS = 5;

	@FXML
	/**
	 * method which is called when controller is created
	 */
	private void initialize() {
		initInstruction("Home.txt");
		initOperations();
		initTableView(TABLE_ROWS, TABLE_COLUMNS);
		initSpinners();
		initFunctionalButtons();
		initDatePicker();
		initTableValueChart();
		initNaviLabels();
	}

	private void initInstruction(String fileName) {
		InstructionReader instructionReader = new InstructionReader(fileName);
		setInstructText(instructionReader.getInstructionFromFile());
	}

	private void initNaviLabels() {
		int i = 0;
		for (Node n : naviLabelsPane.getChildren()) {
			if (!n.getClass().equals(Label.class))
				continue;
			if (i % 2 == 0)
				((Label) n).setStyle("-fx-background-color: white;");

			n.setOnMouseClicked((x) -> naviLabelClicked((Label) n));
			i++;
		}
	}

	private void naviLabelClicked(Label n) {
		String fileName = n.getText() + ".txt";
		initInstruction(fileName);
	}

	private void setInstructText(String text) {
		instructArea.setText(text);
	}

	/**
	 * Method which initialize chart and sets the data model
	 */
	private void initTableValueChart() {
		MyLogger.getLogger().info("Chart initialization...");
		tableValueChart.getData().clear();
		HashMap<Double, Integer> chartData = (HashMap<Double, Integer>) dataModel.countValuesAmount();
		XYChart.Series<String, Integer> dataSeries;
		for (Map.Entry<Double, Integer> entry : chartData.entrySet()) {
			dataSeries = new XYChart.Series<String, Integer>();
			dataSeries.setName(String.valueOf(entry.getKey()));
			dataSeries.getData()
					.add(new XYChart.Data<String, Integer>(String.valueOf(entry.getKey()), entry.getValue()));
			tableValueChart.getData().add(dataSeries);
		}

	}

	/**
	 * Method which initialize date picker,with right format and date
	 */
	private void initDatePicker() {
		MyLogger.getLogger().info("DatePicker initialization...");
		datePicker.setConverter(new DateConverter());
		datePicker.setValue(LocalDate.now());
		resultArea.appendText(datePicker.getValue().toString() + "\n");
		datePicker.setOnAction((x) -> {
			resultArea.appendText(datePicker.getValue().toString() + "\n");
			MyLogger.getLogger().info("Choosing the date...");
		});
	}

	/**
	 * Method which initialize buttons and set them action handling
	 */
	private void initFunctionalButtons() {
		MyLogger.getLogger().info("Functional buttons initialization...");
		saveToFile.setOnAction((x) -> saveTableToFile());
		zeroing.setOnAction((x) -> zeroingTable());
		okButton.setOnAction(
				(x) -> setValueInTable(valueField.getText(), columnSpinner.getValue(), rowSpinner.getValue()));
	}

	/**
	 * Method which initialize spinner for choosing cell in the table
	 */
	private void initSpinners() {
		MyLogger.getLogger().info("Spinners initialization...");
		rowSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
		columnSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
	}

	/**
	 * Method which initialize list of operations on table values
	 */
	private void initOperations() {
		MyLogger.getLogger().info("Operation list initialization...");
		optionsList = FXCollections.observableArrayList(new OptionList().getOptions());
		operationsList.setItems(optionsList);
		operationsList.getSelectionModel().select(0);
		operationsList
				.setOnMouseClicked((x) -> calcuate(operationsList.getSelectionModel().getSelectedItem().getType()));
	}

	/**
	 * Method which choosing proper method to execute
	 * 
	 * @param type is the type of option according to which proper function is
	 *             called
	 */
	private void calcuate(CalcOptionType type) {
		switch (type) {
		case SUM:
			MyLogger.getLogger().info("Calculating sum...");
			resultArea.appendText(String.valueOf("Sum: " + dataModel.calculateSum()) + "\n");
			break;
		case MIN_MAX:
			MyLogger.getLogger().info("Calculating min and max...");
			resultArea.appendText(dataModel.minAndMax() + "\n");
			break;
		case AVG:
			MyLogger.getLogger().info("Calculating average value...");
			resultArea.appendText(String.valueOf("Average: " + dataModel.calculateAverage()) + "\n");
			break;
		default:
			break;
		}
	}

	/**
	 * Method which initialize data of table
	 * 
	 * @param rowsAmount    it's amount of rows in table
	 * @param columnsAmount it's amount of columns in table
	 */
	private void initTableView(int rowsAmount, int columnsAmount) {
		MyLogger.getLogger().info("Table initialization...");
		dataModel = new TableDataModel<>(rowsAmount, columnsAmount, 0.0);
		tableView.getColumns().addAll(dataModel.getWholeTable());
		tableView.getItems().addAll(dataModel.getRows());
	}

	/**
	 * This methods changes value in particular cell in data model
	 * 
	 * @param sValue      - string value which is parsed in method end set into cell
	 * @param columnIndex - index of chosen column
	 * @param rowIndex    - index of chosen row
	 */
	private void setValueInTable(String sValue, int columnIndex, int rowIndex) {
		try {
			double value = Double.parseDouble(sValue);
			MyLogger.getLogger()
					.info("Setting value " + value + " at: " + rowIndex + " row and " + columnIndex + " column");
			tableView.getItems().set(rowIndex,
					dataModel.replaceValue(tableView.getItems().get(rowIndex), columnIndex, rowIndex, value));

			initTableValueChart();
		} catch (Exception e) {
			showAlert("Please input correct value !");
		}
	}

	/**
	 * Method which show dialog alert after error
	 * 
	 * @param message - message wich is showed int alert dialog
	 */
	private void showAlert(String message) {
		MyLogger.getLogger().info("Showing error...");
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	/**
	 * Method which reset values of table
	 */
	private void zeroingTable() {
		MyLogger.getLogger().info("Clearing table...");
		tableView.getColumns().clear();
		tableView.getItems().clear();
		initTableView(TABLE_ROWS, TABLE_COLUMNS);
		initTableValueChart();
	}

	@FXML
	/**
	 * Method which saving value of table to file by filechooser
	 */
	private void saveTableToFile() {
		MyLogger.getLogger().info("Saving table to file...");
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
		File file = chooser.showSaveDialog(new Stage());
		FileWriter writer;
		try {
			if (file == null)
				return;
			writer = new FileWriter(file);
			System.out.println(file + "\n" + tableView.getItems().toString());
			writer.write(tableView.getItems().toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	/**
	 * Method that close application
	 */
	private void closeApp() {
		MyLogger.getLogger().info("Closing application...");
		System.exit(0);
	}

	@FXML
	/**
	 * Method that clear logs
	 */
	private void clearingLog() {
		MyLogger.getLogger().info("Clearing application log...");
		resultArea.setText("");
	}

	@FXML
	/**
	 * Method that show window which contains info about application
	 */
	private void showAbout() {
		MyLogger.getLogger().info("Showing about...");
		Platform.runLater(() -> PopupWindowController.showPopupWindow("/resources/AboutDialog.fxml", "About"));
	}

	@FXML
	/**
	 * Method that show window which contains tip
	 */
	private void showTip() {
		MyLogger.getLogger().info("Showing tips...");
		Platform.runLater(() -> PopupWindowController.showPopupWindow("/resources/TipDialog.fxml", "Tips"));
	}
}
