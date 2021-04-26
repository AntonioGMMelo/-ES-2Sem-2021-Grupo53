package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;


import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application{

	GridPane grid = new GridPane();
	Scene scene = new Scene(grid, 300, 250);
	
	Label metrics;
	Label lOperator;
	
	ComboBox metriCB;
	ComboBox cb1;
	
	String firstMetric = "is_god_class";
	String secondMetric = "is_long_method";
	
	
	
	
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * metric he wants to use.
	 * */
	@SuppressWarnings("unchecked")
	private void chooseMetrics() {
		String[] metricList = {firstMetric, secondMetric};

		metrics = new Label("Choose Metrics");
		metriCB = new ComboBox(FXCollections.observableArrayList(metricList));
		grid.add(metrics, 1, 1);
		grid.add(metriCB, 1, 2);
	}
	
	/**
	 * This method creates a text field
	 * so the user can choose
	 * the limit wanted for said metric
	 * */
	private void chooseLimits() {
		Label l = new Label("Choose the limits:" );
		grid.add(l, 2, 1);
		TextField choice = new TextField();
		choice.setEditable(true);
		choice.setPrefSize(5.0, 5.0);
		choice.autosize();
		grid.add(choice, 2, 2);
	}
	
	/**
	 * This method creates a combo box
	 * so the user can choose the 
	 * logic operator to form the logic expressions
	 * he'll use with the metric chosen 
	 * */
	private void logicOperator() {
		String logicOp[] = { "AND", "OR" };
		lOperator = new Label("Logic Operator");
		cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
		grid.add(lOperator, 1, 4);
		grid.add(cb1, 1, 5);
	}
	
	/**
	 * This method creates a text field
	 * so the user can choose
	 * the limit wanted for said metric
	 * */
	private void otherLimit() {
		TextField choice = new TextField();
		choice.setEditable(true);
		choice.setPrefSize(5.0, 5.0);
		choice.autosize();
		grid.add(choice, 2, 7);
	}
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * metric he wants to use.
	 * */
	@SuppressWarnings("unchecked")
	private void otherMetric() {
		TextField otherMetric = new TextField();
		otherMetric.setEditable(false);
		grid.add(otherMetric, 1, 7);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream( "icon.png")));
		primaryStage.setTitle("Code Smells");
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
        launch(args);
    }
}
