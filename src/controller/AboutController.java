package controller;

import application.MyLogger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController {

	@FXML
	private Button closeButton;

	@FXML
	/**
	 * Method which initialize components when controller is made
	 */
	private void initialize() {
		closeButton.setOnAction((x) -> closeWindow());
	}

	/**
	 * Method which close the window when close button is clicked
	 */
	private void closeWindow() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		MyLogger.getLogger().info("About window is closed");
	}
}
