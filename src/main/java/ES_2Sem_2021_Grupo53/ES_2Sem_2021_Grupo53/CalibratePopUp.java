package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CalibratePopUp{

	public static void display(int[] Statistics) {
		
		Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		double tPRate = ((double)Statistics[1]/((double)Statistics[0] + (double)Statistics[1])) * 100;
		double tFRate = ((double)Statistics[3]/((double)Statistics[2] + (double)Statistics[3])) * 100 ;
		double correctRate = (((double)Statistics[1] + (double)Statistics[3])/((double)Statistics[0] + (double)Statistics[1] + (double)Statistics[2] + (double)Statistics[3])) * 100;
		
		Text l = new Text();
		l.setText("These Rules Have " + Statistics[1]  + " True positives and " + Statistics[0] + " False Positives giving us a True Positives rate of: " + 
		tPRate + "% they also have " + Statistics[3] + " True Negatives and " + Statistics[2] + " False Negatives, which gives us a True Negative rate of: " + 
		tFRate + "% which ultimately gives us a correct rate of:  " + correctRate + "% for these rules." );
		l.setWrappingWidth(250);
		l.setTextAlignment(TextAlignment.JUSTIFY);
		pane.setCenter(l);
		
		HBox grid = new HBox();
		
		Button reCalibrate = new Button("Re-Calibrate");
		Button save = new Button("Save");
		
		grid.getChildren().add(reCalibrate);
		grid.getChildren().add(save);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(0,0,10,0));
		
		BorderPane helper = new BorderPane();
		helper.setCenter(grid);
		
		pane.setBottom(helper);
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		
	}

}
