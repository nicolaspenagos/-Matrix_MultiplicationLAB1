package userinterface;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    @FXML
    public void initialize(){
    	
    	grid1 = new GridPane();
    	grid2 = new GridPane();
    	grid3 = new GridPane();
    	
    	grid1.setGridLinesVisible(true);
    	grid2.setGridLinesVisible(true);
    	grid3.setGridLinesVisible(true);
    
        int[][] m = new int[5][5];
        for(int i=0; i<5; i++) {
        	for(int j=0; j<5; j++) {
        		m[i][j]=1;
        		grid1.addColumn(i);
        		grid1.addRow(i);
        		grid2.addColumn(i);
        		grid2.addRow(i);
        		grid3.addColumn(i);
        		grid3.addRow(i);
        		Label lx = new Label();
        		Label lx1 = new Label();
        		Label lx2 = new Label();
        		lx.setText(" 1 ");
        		lx1.setText(" 1 ");
        		lx2.setText(" 1 ");
        		grid1.add(lx, i, j);
        		grid2.add(lx1, i, j);
        		grid3.add(lx2, i, j);
        		
        	}
        }
       
        scrollPane1.setContent(grid1);
        scrollPane2.setContent(grid2);
        scrollPane3.setContent(grid3);
      
      
        
        
    }


}
