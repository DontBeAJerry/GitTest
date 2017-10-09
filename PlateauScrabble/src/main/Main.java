package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.PlateauController;

public class Main extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	String[] chevalet = {"a","b","c","d","e","f","g"};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Plateau de Scrabble");

		initRootLayout();
		showPlateauVue();

	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public String[] getChevalet() {
		return chevalet;
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPlateauVue() {
		 try {
	            // Load plateau overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("PlateauVue.fxml"));
	            AnchorPane plateauVue = (AnchorPane) loader.load();

	            // Set plateau overview into the center of root layout.
	            rootLayout.setCenter(plateauVue);
	            
	         // Give the controller access to the main app.
	            PlateauController controller = loader.getController();
	            controller.setMainApp(this);

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

}
