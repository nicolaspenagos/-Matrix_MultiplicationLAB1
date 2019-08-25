package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.BattleBoard;

import java.io.FileNotFoundException;
import java.util.Optional;

import customThreads.GUIUpdateControlThread;

public class GUIController {

	@FXML
	private ScrollPane scrollPane1;

	@FXML
	private ScrollPane scrollPane2;

	@FXML
	private ScrollPane scrollPane3;

	@FXML
	private TextField howManyTxt;

	@FXML
	private TextField txtFRows1;

	@FXML
	private TextField txtFColumns1;

	@FXML
	private TextField txtFRows2;

	@FXML
	private TextField txtFColumns2;

	@FXML
	private Button generateButton;

	@FXML
	private Button multiplyButton;

	private GridPane grid1;

	private GridPane grid2;

	private GridPane grid3;
	
	private GridPane grid4;

	private BattleBoard battleBoard;

	@FXML
	public void initialize() {

		GUIUpdateControlThread gut = new GUIUpdateControlThread(this);
		gut.setDaemon(true);
		gut.start();

		txtFRows2.setEditable(false);

		grid1 = new GridPane();
		grid2 = new GridPane();
		grid3 = new GridPane();
		grid4 = new GridPane();

		grid1.setGridLinesVisible(true);
		grid2.setGridLinesVisible(true);
		grid3.setGridLinesVisible(true);
		grid4.setGridLinesVisible(true);

		battleBoard = new BattleBoard();

	}

	public void update() {

		try {

			int columnsFirstMatrix = Integer.parseInt(txtFColumns1.getText());

			if (columnsFirstMatrix != 0 && columnsFirstMatrix > 0) {
				txtFRows2.setText(String.valueOf(columnsFirstMatrix));
			}

		} catch (NumberFormatException e) {

		}

	}

	@FXML
	void generate(ActionEvent event) {
		cleanResult();
		try {

			int rows1 = Integer.parseInt(txtFRows1.getText());
			int columns1 = Integer.parseInt(txtFColumns1.getText());

			int rows2 = Integer.parseInt(txtFRows2.getText());
			int columns2 = Integer.parseInt(txtFColumns2.getText());

			boolean repeat = false;

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Please answer");
			alert.setContentText("Can the numbers be repeated?");

			ButtonType buttonTypeOne = new ButtonType("YES");
			ButtonType buttonTypeTwo = new ButtonType("NO");
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				repeat = true;
			}

			battleBoard.generateRandomMatrix(rows1, columns1, rows2, columns2, repeat);
			fillAndShowMatrix();

		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Information");
			alert.setHeaderText("WARNING!");
			alert.setContentText("Please proveide all of the information required to generate the matrix");

			alert.showAndWait();
		}
		
	}

	public void fillAndShowMatrix() {

		int[][] matrix1 = battleBoard.getMatrix1();
		int[][] matrix2 = battleBoard.getMatrix2();

		GridPane gridX = new GridPane();
		GridPane gridY = new GridPane();
		gridX.setGridLinesVisible(true);
		gridY.setGridLinesVisible(true);
		grid1 = gridX;
		grid2 = gridY;

		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[0].length; j++) {

				grid1.addColumn(i);
				grid1.addRow(j);

				Label lx = new Label();

				lx.setText(" " + matrix1[i][j] + " ");

				grid1.add(lx, j, i);

			}
		}

		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {

				grid2.addColumn(i);
				grid2.addRow(j);

				Label lx = new Label();

				lx.setText(" " + matrix2[i][j] + " ");

				grid2.add(lx, j, i);

			}
		}

		scrollPane1.setContent(grid1);
		scrollPane2.setContent(grid2);

	}

    @FXML
    void generateListOfMatrices1(ActionEvent event) {
    	cleanMatrices();
    	int n = Integer.parseInt(howManyTxt.getText());
    	
    	try {
			battleBoard.generateRamdonMatrices(n);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int[][] matrix = battleBoard.getResult();
		
		GridPane gridX = new GridPane();
		grid4 = gridX;
		grid4.setGridLinesVisible(true);
	

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {

				grid4.addColumn(i);
				grid4.addRow(j);

				Label lx = new Label();

				lx.setText(" " + matrix[i][j] + " ");

				grid4.add(lx, j, i);

			}
		}
		scrollPane3.setContent(grid4);
    	
    }

	@FXML
	public void multiply(ActionEvent event) {
		try {
			battleBoard.componetToComponetMultiplier();
			int[][] result = battleBoard.getResult();

			GridPane gridX = new GridPane();
			gridX.setGridLinesVisible(true);
			grid3 = gridX;

			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[0].length; j++) {

					grid3.addColumn(i);
					grid3.addRow(j);

					Label lx = new Label();

					lx.setText(" " + result[i][j] + " ");
					if (battleBoard.isPrimeNumber(result[i][j]))
						lx.setTextFill(Color.DODGERBLUE);
					grid3.add(lx, j, i);

				}
			}

			scrollPane3.setContent(grid3);
		} catch (NumberFormatException n) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Information");
			alert.setHeaderText("WARNING!");
			alert.setContentText("Please proveide all of the information required");

			alert.showAndWait();
		} catch (NullPointerException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Information");
			alert.setHeaderText("WARNING!");
			alert.setContentText("Please proveide all of the information required");

			alert.showAndWait();
		}

	}

	public void cleanResult() {

		GridPane gridX = new GridPane();
		scrollPane3.setContent(gridX);

	}
	
	public void cleanMatrices() {

		GridPane gridX = new GridPane();
		scrollPane1.setContent(gridX);
		scrollPane2.setContent(gridX);

	}

}
