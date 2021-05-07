package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	private static boolean isTrue = false;
	
	private static void helper() {
		
		isTrue = true;
		
	}
	/**
	 * This method receives an array of integers and creates statistics form them
	 * 
	 * It receives an array with 4 integers False Positives, True Positives, False Negatives and True Negatives and
	 * uses them to display the correct statistics for the rule set defined by the user,
	 * from there the user can either save these rules and go to the main menu or
	 * go back to the rule definition to re-calibrate the rule.
	 * 
	 * @param Statistics
	 * @return a string to be displayed where it was called
	 */
	public static String display(int[] Statistics) {
		
		final Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		double tPRate = ((double)Statistics[1]/((double)Statistics[0] + (double)Statistics[1])) * 100;
		double tFRate = ((double)Statistics[3]/((double)Statistics[2] + (double)Statistics[3])) * 100 ;
		double correctRate = (((double)Statistics[1] + (double)Statistics[3])/((double)Statistics[0] + (double)Statistics[1] + (double)Statistics[2] + (double)Statistics[3])) * 100;
		final String myString = "These Rules Have " + Statistics[1]  + " True positives and " + Statistics[0] + " False Positives giving us a True Positives rate of: " + 
				tPRate + "% they also have " + Statistics[3] + " True Negatives and " + Statistics[2] + " False Negatives, which gives us a True Negative rate of: " + 
				tFRate + "% which ultimately gives us a correct rate of:  " + correctRate + "% for these rules.";
		
		Text l = new Text();
		l.setText(myString);
		l.setWrappingWidth(250);
		l.setTextAlignment(TextAlignment.JUSTIFY);
		pane.setCenter(l);
		
		HBox grid = new HBox();
		
		Button reCalibrate = new Button("Re-Calibrate");
		Button save = new Button("Save");
		
		reCalibrate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				primaryStage.close();
				
			}
			
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				helper();
				primaryStage.close();
			}
	
		});
		
		grid.getChildren().add(reCalibrate);
		grid.getChildren().add(save);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(0,0,10,0));
		
		BorderPane helper = new BorderPane();
		helper.setCenter(grid);
		
		pane.setBottom(helper);
		
		primaryStage.setResizable(false);
		Scene scene = new Scene(pane, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
	
		if(isTrue) return "MainMenu";
		
		return myString;
		
	}

}
