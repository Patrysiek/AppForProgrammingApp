package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is singleton controller for showing popup windows of this application
 * 
 * @author Patryk Kłos
 * @version 1.1
 * @version 2019-04-19
 */
public class PopupWindowController {

	private PopupWindowController() {
	}

	/**
	 * 
	 * @param layoutPath Parameter which indicate on file with layout
	 * @param title      Title of the pop up window
	 */
	public static void showPopupWindow(String layoutPath, String title) {
		FXMLLoader fxmlLoader = new FXMLLoader(PopupWindowController.class.getResource(layoutPath));
		Parent parent;
		try {
			parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
