package application;

import controller.PopupWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Class that is being launched first. It starts the scenes of application.
 * 
 * @author Patryk Kłos
 * @version 1.0
 * @version 2019-03-22
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			MyLogger.getLogger().info("Application's start...");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MainLayout.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 1100, 500);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/resources/logo.png"));
			primaryStage.setTitle("Project app");
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(event -> System.exit(0));
			primaryStage.show();
			MyLogger.getLogger().info("Setting scene,showing tips and main window");
			showWithDelay(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showWithDelay(int delay) {
		MyLogger.getLogger().info("Showing about window");
		Platform.runLater(() -> {
			try {
				Thread.sleep(delay);
				PopupWindowController.showPopupWindow("/resources/TipDialog.fxml", "Tips");
			} catch (InterruptedException e) {
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
