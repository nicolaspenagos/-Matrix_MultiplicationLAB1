package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.BattleBoard;
import customThreads.GUIUpdateControlThread;

public class GUIController {

	@FXML
	private ScrollPane scrollPane1;

	@FXML
	private ScrollPane scrollPane2;

	@FXML
	private ScrollPane scrollPane3;

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

		grid1.setGridLinesVisible(true);
		grid2.setGridLinesVisible(true);
		grid3.setGridLinesVisible(true);

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

		try {

			int rows1 = Integer.parseInt(txtFRows1.getText());
			int columns1 = Integer.parseInt(txtFColumns1.getText());

			int rows2 = Integer.parseInt(txtFRows2.getText());
			int columns2 = Integer.parseInt(txtFColumns2.getText());

			battleBoard.generateRandomMatrices(rows1, columns1, rows2, columns2);
			fillAndShowMatrices();

		} catch (NumberFormatException e) {

		}

	}

	public void fillAndShowMatrices() {
		
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

				grid1.add(lx, i, j);

			}
		}

		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {

				grid2.addColumn(i);
				grid2.addRow(j);

				Label lx = new Label();

				lx.setText(" "+matrix2[i][j]+" ");
				
				grid2.add(lx, i, j);

			}
		}

		scrollPane1.setContent(grid1);
		scrollPane2.setContent(grid2);
	
	}

}
