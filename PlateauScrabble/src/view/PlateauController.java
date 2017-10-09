package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;

public class PlateauController {

	@FXML
	private Label lettre1;
	@FXML
	private Label lettre2;
	@FXML
	private Label lettre3;
	@FXML
	private Label lettre4;
	@FXML
	private Label lettre5;
	@FXML
	private Label lettre6;
	@FXML
	private Label lettre7;
	@FXML
	private GridPane plateau;

	// Reference to the main application.
	private Main mainApp;

	public PlateauController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialise le chevalet avec des valeurs vides.
		showChevalet(null);

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Affiche le Chevalet
		showChevalet(null);

	}

	private void showChevalet(String[] ch) {
		if (ch != null) {
			lettre1.setText(mainApp.getChevalet()[0]);
			lettre2.setText(mainApp.getChevalet()[1]);
			lettre3.setText(mainApp.getChevalet()[2]);
			lettre4.setText(mainApp.getChevalet()[3]);
			lettre5.setText(mainApp.getChevalet()[4]);
			lettre6.setText(mainApp.getChevalet()[5]);
			lettre7.setText(mainApp.getChevalet()[6]);
		} else {
			lettre1.setText(null);
			lettre2.setText(null);
			lettre3.setText(null);
			lettre4.setText(null);
			lettre5.setText(null);
			lettre6.setText(null);
			lettre7.setText(null);
		}
	}
	
	
	
	/**
	 * Appeler quand le joueur appuie sur Nouvelle Partie
	 * Initialise la grille vide et le chevalet avec 7 lettres
	 */
	@FXML
	private void handleNewGame() {

		if (mainApp.getChevalet() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Chevalet Vide");
			alert.setHeaderText("Vous n'avez plus de pièces à jouer");
			alert.setContentText("Veuillez piocher des lettres");

			alert.showAndWait();
		} else
			showChevalet(mainApp.getChevalet());

	}
	
	@FXML
	private void handleSetCase() {
		
		
		
		for(int i=0; i<10; i++)
		System.out.print(plateau.getChildren().get(0).toString());
		
	}
	
	@FXML
	private void test() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Chevalet Vide");
		alert.setHeaderText("Vous n'avez plus de pièces à jouer");
		alert.setContentText("Veuillez piocher des lettres");
	}

}
