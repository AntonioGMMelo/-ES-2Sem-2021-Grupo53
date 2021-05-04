package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorMessage {

	public static void display(String message) {
		
		Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		BorderPane pain = new BorderPane();
		
		Text t = new Text();
		t.setText(message);
		
		t.setWrappingWidth(280);
		
		Button ok = new Button("Ok");
		
		ok.setOnAction( e->{
			
			primaryStage.close();
			
		});
		

		pain.setMargin(ok, new Insets(0,0,10,135));
		pain.setCenter(ok);
		
		pane.setCenter(t);
		pane.setBottom(ok);
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		
	}
	
}
