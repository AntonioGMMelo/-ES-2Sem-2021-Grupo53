package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application{

	Label metrics;
	Label lOperator;
	Label limits;
	
	ComboBox metriCB;
	ComboBox cb1;
	
	TextField mLimits;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Code Smells");
		
		metrics = new Label("Choose Metrics");
		metriCB = new ComboBox();
		
		limits = new Label("Choose limits");
		mLimits = new TextField();
		
		String logicOp[] =
            { "AND", "OR" };
		
		lOperator = new Label("Choose Logic Operator");
		cb1 = new ComboBox(FXCollections.observableArrayList(logicOp));
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		grid.add(metrics, 1, 1);
		grid.add(limits, 1, 2);
		grid.add(mLimits, 2, 2);
		grid.add(lOperator, 1, 3);
		
		grid.add(metriCB, 2, 1);
		grid.add(cb1, 2, 3);
		
		
		Scene scene = new Scene(grid, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
