package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ghost extends Application  {
	/**
	 * Method that exists to circumvent launchin application twice
	 * 
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception {
	
		primaryStage.setScene(new Scene(new GridPane(),1,1));
		MyGUI.display(new Stage());
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
