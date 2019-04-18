package controller;

import application.MyLogger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.TipModel;

/**
 * This is controller for TipDialog.fxml file with layout
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class TipOfTheDayController {

	@FXML
	private Button nextTip, prevTip, closeButton;
	@FXML
	private TextArea tipArea;
	/**
	 * @see model.TipModel
	 */
	private TipModel tipModel;

	@FXML
	/**
	 * Method which initialize components when controller is made
	 */
	private void initialize() {
		tipModel = new TipModel();
		Platform.runLater(() -> tipArea.setText(tipModel.initRandomTip()));
		nextTip.setOnAction((x) -> tipArea.setText(tipModel.getNext()));
		prevTip.setOnAction((x) -> tipArea.setText(tipModel.getPrevious()));
		closeButton.setOnAction((x) -> closeWindow());
	}

	/**
	 * Method which close the window when close button is clicked
	 */
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		MyLogger.getLogger().info("Window with about is closed");
	}

}
