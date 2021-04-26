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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application{

	GridPane grid = new GridPane();
	Scene scene = new Scene(grid, 300, 250);

	Stage secondaryStage = new Stage();
	GridPane secGrid = new GridPane();
	Scene secondScene = new Scene(secGrid, 250, 200);
	
	String firstMetric = "is_god_class";
	String secondMetric = "is_long_method";
	
	Button submmit;
	Button pathButton;
	
	DirectoryChooser directaryChooser = new DirectoryChooser();
	
	/**
	 * This method creates a combo box
	 * so the user can chose the 
	 * metric he wants to use.
	 * */
	@SuppressWarnings("unchecked")
	private void chooseMetrics() {
		String[] metricList = {firstMetric, secondMetric};

		Label metrics = new Label("Choose Metrics");
		ComboBox metriCB = new ComboBox(FXCollections.observableArrayList(metricList));
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
		Label lOperator = new Label("Logic Operator");
		ComboBox cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
		grid.add(lOperator, 1, 4);
		grid.add(cb1, 1, 5);
	}
	
	/**
	 * This method creates a text field
	 * so the user can choose
	 * the limit wanted for the other metric
	 * */
	private void otherLimit() {
		TextField choice = new TextField();
		choice.setEditable(true);
		choice.setPrefSize(5.0, 5.0);
		choice.autosize();
		grid.add(choice, 2, 7);
	}
	
	/**
	 * This method creates a text field
	 * that shows the other metric.
	 * */
	@SuppressWarnings("unchecked")
	private void otherMetric() {
		TextField otherMetric = new TextField();
		otherMetric.setEditable(false);
		grid.add(otherMetric, 1, 7);
	}
	
	/**
	 * This method creates a button that 
	 * redirects the user to a second stage
	 * where e'll be able to define path*/
	private void submmitButton() {
		submmit = new Button ("Submmit");
		grid.add(submmit, 2, 8);
	}
	
	/** This method redirects the user
	 * to a new window where he will choose
	 * the metrics, threshold and logic operator
	 * to work with :)
	 * */
	@SuppressWarnings("unchecked")
	private void submmitButtonAction() {
		submmit.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream( "icon2.png")));
				secondaryStage.setTitle("Settings");
				secGrid.setHgap(2);
				secGrid.setVgap(3);
				
				TextArea area = new TextArea();
				area.setEditable(true);
				grid.add(area, 1, 1);
				
				TextField path = new TextField();
				path.setEditable(true);
				grid.add(path, 1,199);
				
				pathButton = new Button ("Your Path");
				grid.add(path, 2, 199);
				
				secondaryStage.setScene(secondScene);
				secondaryStage.sizeToScene();
				secondaryStage.show();				 
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void pathButtonAction() {
		pathButton.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				directaryChooser.setTitle("Open Resource Directory");
				File selectedDirectory = directaryChooser.showDialog(secondaryStage);
				String string = selectedDirectory.getAbsolutePath();
				TextField pathField = new TextField(string);
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream( "icon.png")));
		primaryStage.setTitle("Code Smells");
		
		grid.setHgap(10);
		grid.setVgap(10);
	
		chooseMetrics() ;
		chooseLimits();
		logicOperator();
		otherLimit();
		otherMetric();
		submmitButton();
				
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

		
		public static void main(String[] args) {
        launch(args);
    }
}
