package view;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.Main;

public class PlateauController {

	static String s;

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
	private Label testGetLabel;
	@FXML
	private GridPane plateau;
	@FXML
	private GridPane chevalet;

	// Reference to the main application.
	private Main mainApp;

	public PlateauController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Affiche l'image plateau
		Image img = new Image("/imgView/plateau.png");
		BackgroundSize bs = new BackgroundSize(plateau.getWidth(), plateau.getHeight(), false, false, true, true);
		BackgroundImage myBI = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, bs);
		plateau.setBackground(new Background(myBI));

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {

				Label label = new Label("o");
				label.setMouseTransparent(true);
				GridPane.setHalignment(label, HPos.CENTER);
				GridPane.setRowIndex(label, i);
				GridPane.setColumnIndex(label, j);

				plateau.getChildren().add(label);
			}
		}

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Affiche le Chevalet
		showChevalet(mainApp.getChevalet());

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
	 * Appeler quand le joueur appuie sur Nouvelle Partie Initialise la grille
	 * vide et le chevalet avec 7 lettres
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
	private void dragGetLabel() {

		Node l = chevalet.getChildren().get(0);
		String[] ts = l.toString().split("'");
		s = ts[1];
		System.out.println("1 : " + s);

	}

	@FXML
	private void dragSetLabel() {
		System.out.println(s);
		testGetLabel.setText(s);
	}

	// TODO

	@FXML
	public void onDrag(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture */
		System.out.println("onDragDetected");
		Label source = (Label) event.getSource();

		/*
		 * Integer colIndex = GridPane.getColumnIndex(source); Integer x =
		 * (Integer) source.getProperties().get("gridpane-column");
		 */

		/* allow any transfer mode */
		Dragboard db = source.startDragAndDrop(TransferMode.ANY);

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(source.getText());
		db.setContent(content);

		event.consume();
	}

	// DROP *******************************

	public void onDragOver(DragEvent event) {
		/* data is dragged over the target */
		System.out.println("onDragOver");
		Label source = (Label) event.getSource();
		Label target = (Label) event.getTarget();
		/*
		 * accept it only if it is not dragged from the same node and if it has
		 * a string data
		 */
		if (event.getGestureSource() != target && event.getDragboard().hasString()) {
			/*
			 * allow for both copying and moving, whatever user chooses
			 */
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}

		event.consume();
	}

	public void onDragEntered(DragEvent event) {
		/* the drag-and-drop gesture entered the target */
		System.out.println("onDragEntered");
		Label source = (Label) event.getSource();
		Label target = (Label) event.getTarget();
		// System.out.println("onDragEntered");
		/* show to the user that it is an actual gesture target */
		if (event.getGestureSource() != target && event.getDragboard().hasString()) {
			System.out.println(event.getDragboard().getString());
			BackgroundFill bf = new BackgroundFill(Color.rgb(17, 119, 255), CornerRadii.EMPTY, Insets.EMPTY);
			Background bg = new Background(bf);
			target.setBackground(bg);
		}

		event.consume();
	}

	public void onDragExited(DragEvent event) {
		
		Label target = (Label) event.getTarget();
		/* mouse moved away, remove the graphical cues */
		BackgroundFill bf = new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY);
		Background bg = new Background(bf);
		target.setBackground(bg);

		event.consume();
	}

	public void onDragDropped(DragEvent event) {
		/* data dropped */
		System.out.println("onDragDropped");
		Label target = (Label) event.getTarget();
		/* if there is a string data on dragboard, read it and use it */
		Dragboard db = event.getDragboard();
		boolean success = false;
		if (db.hasString()) {
			target.setText(db.getString());
			success = true;
		}
		/*
		 * let the source know whether the string was successfully transferred
		 * and used
		 */
		event.setDropCompleted(success);

		event.consume();
	}

	public void onDragDone(DragEvent event) {
		/* the drag-and-drop gesture ended */
		Label source = (Label) event.getSource();

		System.out.println("onDragDone");
		/* if the data was successfully moved, clear it */
		if (event.getTransferMode() == TransferMode.MOVE) {
			source.setText("");
		}

		event.consume();
	}

}
