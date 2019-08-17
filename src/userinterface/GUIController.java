package userinterface;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class GUIController {
	
    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private ScrollPane scrollPane2;

    @FXML
    private ScrollPane scrollPane3;
    
    GridPane grid1 = new GridPane();
    
    GridPane grid2 = new GridPane();
    
    GridPane grid3 = new GridPane();
    

    @FXML
    public void initialize(){
   
    	grid1.setGridLinesVisible(true);
    	grid2.setGridLinesVisible(true);
    	grid3.setGridLinesVisible(true);
    	grid1.setAlignment(Pos.CENTER);
    	
    
        int[][] m = new int[15][15];
        for(int i=0; i<15; i++) {
        	for(int j=0; j<15; j++) {
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
