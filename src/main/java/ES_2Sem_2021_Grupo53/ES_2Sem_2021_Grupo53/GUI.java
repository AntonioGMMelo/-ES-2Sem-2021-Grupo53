package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application{

	GridPane grid = new GridPane();
	Scene scene = new Scene(grid, 300, 250);
	
	Stage secondaryStage = new Stage();
	GridPane secGrid = new GridPane();
	Scene secondScene = new Scene(secGrid, 200, 100);
	
	Label metrics;
	Label lOperator;

	ComboBox metriCB;
	ComboBox cb1;
	
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * metric he wants to use.
	 * */
	private void chooseMetrics() {
		String metricTest[] = { "metric1", "metric2", "metric3", "metric4" };
		metrics = new Label("Choose Metrics");
		metriCB = new ComboBox(FXCollections.observableArrayList(metricTest));
		metricCBAtion();
	}
	
	/**Once you choose a metric 
	 * you are redirected
	 * to a new window so you can 
	 * choose a limit for the metric 
	 * chosen. :D*/
	@SuppressWarnings("unchecked")
	private void metricCBAtion() {
		metriCB.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.setTitle("Choose Limits");
				Label metChosen = new Label((String) metriCB.getValue());			
				secGrid.add(metChosen, 2, 1);

				Spinner choice = new Spinner(0, 1000, 0);
				choice.setEditable(true);
				secGrid.add(choice, 2, 2);
				
				Button ok = new Button("Ok");
				Button cancel = new Button ("Cancel");
				secGrid.add(ok, 2,4);
				secGrid.add(cancel, 3, 4);
				

				secondaryStage.setScene(secondScene);
				secondaryStage.show();
			}
		});
	}
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * logic operator to form logic expressions
	 * he'll use with the metric chosen 
	 * */
	private void logicOperator() {
		String logicOp[] = { "AND", "OR" };
		lOperator = new Label("Choose Logic Operator");
		cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Code Smells");
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		chooseMetrics();		
		logicOperator();
		
		grid.add(metrics, 1, 1);
		grid.add(lOperator, 1, 3);
		
		grid.add(metriCB, 2, 1);
		grid.add(cb1, 2, 3);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}

}
