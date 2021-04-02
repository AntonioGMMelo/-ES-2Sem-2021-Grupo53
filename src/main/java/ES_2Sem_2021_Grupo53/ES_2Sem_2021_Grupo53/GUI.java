package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application{

	Button button;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("GUI");
		
		button = new Button();
		button.setText("Get Path");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void main(String[] args) {
		launch(args);
	}

}
