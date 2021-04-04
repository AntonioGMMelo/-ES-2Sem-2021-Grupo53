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
	Label limits;
	
	ComboBox metriCB;
	ComboBox cb1;
	
	Button choose;
	
	/**
	 * This button has to redirect the user to a form 
	 * so he can set limits of the metric chosen!.
	 * */
	@SuppressWarnings("unchecked")
	private void chooseButtonAction() {
		choose.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				secondaryStage.setTitle("Choose Limits");
				Label metChosen = new Label("Metric chosen: ");				
				secGrid.add(metChosen, 1, 1);
				
				Spinner choice = new Spinner(0, 1000, 0);
			    choice.setEditable(true);
				secGrid.add(choice, 1, 2);
			
				
				secondaryStage.setScene(secondScene);
				secondaryStage.show();
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Code Smells");
		
		metrics = new Label("Choose Metrics");
		metriCB = new ComboBox();
		
		limits = new Label("Choose limits");
		choose = new Button("Go!");
		chooseButtonAction();
		
		String logicOp[] =
            { "AND", "OR" };
		
		lOperator = new Label("Choose Logic Operator");
		cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
		
		
		grid.setHgap(10);
		grid.setVgap(10);
		
		grid.add(metrics, 1, 1);
		grid.add(limits, 1, 2);
		grid.add(choose, 2, 2);
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
