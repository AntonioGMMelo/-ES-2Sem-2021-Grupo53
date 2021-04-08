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
	Scene secondScene = new Scene(secGrid, 250, 200);
	
	Label metrics;
	Label lOperator;
	Label pathLabel;

	ComboBox metriCB;
	ComboBox cb1;
	
	Button path;
	Button ok;
	Button cancel;
	
	TextField pathField;
	
	
	/**
	 * This method creates the layout
	 * for the user to indicate the
	 * wanted path*/
	private void chooseYourPath() {
		pathLabel = new Label("Tell us your path!");
		pathField = new TextField("Tell us your path here!");
		path = new Button ("path");
		pathBttonAction();
	}
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * metric he wants to use.
	 * */
	private void chooseMetrics() {
		String metricListExemple[] = { "metric1", "metric2", "metric3", "metric4" };
		metrics = new Label("Choose Metrics");
		metriCB = new ComboBox(FXCollections.observableArrayList(metricListExemple));
		secGrid.add(metrics, 1, 1);
		secGrid.add(metriCB, 1, 2);
	}
	
	/**
	 * This method creates a spinner
	 * so the user can indicate
	 * the limit wanted for said metric
	 * */
	private void chooseLimits() {
		Label l = new Label("Choose the limits:" );
		secGrid.add(l, 1, 4);
		Spinner choice = new Spinner(0, 1000, 0);
		choice.setEditable(true);
		secGrid.add(choice, 1, 5);
	}
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * logic operator to form the logic expressions
	 * he'll use with the metric chosen 
	 * */
	private void logicOperator() {
		String logicOp[] = { "AND", "OR" };
		lOperator = new Label("Choose Logic Operator");
		cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
		secGrid.add(lOperator, 1, 6);
		secGrid.add(cb1, 1, 7);
	}
	
	//Exit buttons for the second window
	private void exitButtons() {
		ok = new Button("Ok");
		cancel = new Button ("Cancel");
		secGrid.add(ok, 1, 11);
		secGrid.add(cancel, 4, 11);
		exitButtonsAction();
	}
	
	/** This method redirects the user
	 * to a new window where he will choose
	 * the metrics, threshold and logic operator
	 * to work with :)
	 * */
	@SuppressWarnings("unchecked")
	private void pathBttonAction() {
		path.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.setTitle("Choose Metric Wanted ");
				secGrid.setHgap(2);
				secGrid.setVgap(3);
				
				chooseMetrics();
				chooseLimits();
				logicOperator();
				exitButtons();
				
				secondaryStage.setScene(secondScene);
				secondaryStage.sizeToScene();
				secondaryStage.show();
			}
		});
	}
	
	//Makes the exit buttons exit the second stage
	@SuppressWarnings("unchecked")
	private void exitButtonsAction() {
		ok.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.close();
			}
		});
		cancel.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.close();
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Code Smells");
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		chooseYourPath();
		chooseMetrics();		
		logicOperator();
		
		grid.add(pathLabel, 1, 1);
		grid.add(pathField, 1, 2);
		grid.add(path,2, 3);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
