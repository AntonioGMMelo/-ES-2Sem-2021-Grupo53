package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {

	public static void display() {
	
		final Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		HBox pathSubmit = new HBox();
		
		TextField path = new TextField();
		path.setPromptText("Path");
		
		Button Submit = new Button("Submit");
		
		pathSubmit.getChildren().addAll(path, Submit);
		
		VBox buttons = new VBox();
		
		Button Import = new Button("Import xlsx File");
		
		Button reCalibrate = new Button("Re-Calibrate");
		
		buttons.getChildren().addAll(Import, reCalibrate);
		buttons.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		VBox report = new VBox();
		
		Text t = new Text();
		t.setText("This is where your code smells will be reported if they exist");
		
		report.getChildren().add(t);
		report.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		ScrollPane pain = new ScrollPane();
		pain.setContent(report);
		
		pane.setCenter(pain);
		pane.setRight(buttons);
		pane.setBottom(pathSubmit);	
		
		Scene scene = new Scene(pane, 1920, 1000);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
	
		
	}
	
}
