package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CalibratePopUp{

	public static void display() {
		
		Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		
	}

}
