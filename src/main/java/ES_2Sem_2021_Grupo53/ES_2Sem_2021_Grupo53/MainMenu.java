package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MainMenu {

	public static void display(ArrayList<String> orderOfMethods, ArrayList<String> logicMethods, ArrayList<Integer> thresholdsMethods, ArrayList<String> orderOfClass, ArrayList<String> logicClass, ArrayList<Integer> thresholdsClass) {
	
		final Stage primaryStage = new Stage();
		BorderPane pane = new BorderPane();
		
		//Path Area
		HBox pathSubmit = new HBox();
		
		TextField path = new TextField();
		path.setPromptText("Path");
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		Button button = new Button("Browse...");
	    button.setOnAction(e -> {

	    	   File selectedDirectory = directoryChooser.showDialog(primaryStage);
	    	   path.setText(selectedDirectory.getPath());
	    	   
	     });
		
		Button Submit = new Button("Submit");
		
		pathSubmit.getChildren().addAll(path, button, Submit);
		
		//Import and information area
		VBox buttons = new VBox();
		
		Button Import = new Button("Import xlsx File");
		
		Button reCalibrate = new Button("Re-Calibrate");
		
		Text packages = new Text();
		packages.setText("Packages: ");
		
		Text classes = new Text();
		classes.setText("Classes: ");
		
		Text methods = new Text();
		methods.setText("Methods: ");
		
		Text loc = new Text();
		loc.setText("Lines of code: ");
		
		buttons.getChildren().addAll(Import, reCalibrate, packages, classes, methods, loc);
		buttons.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//Code_Smells report area
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
		
		
		Submit.setOnAction(e -> {
			
			int[] generalMetrics = new Metrics().getMetrics(path.getText(), orderOfMethods, logicMethods, thresholdsMethods, orderOfClass, logicClass, thresholdsClass);
			
			packages.setText("Packages: " + generalMetrics[0]);
			classes.setText("Classes: " + generalMetrics[1]);
			methods.setText("Methods: " + generalMetrics[2]);
			loc.setText("Lines of code: " + generalMetrics[3]);
			
			XSSFSheet Sheet = null;
			
			try {
				
				String[] pathHelp = path.getText().split("\\\\");
				String xlsxFileName = pathHelp[pathHelp.length -1];
				
				Sheet = new XSSFWorkbook(new FileInputStream(new File(".\\"+ xlsxFileName +"_metrics.xlsx"))).getSheetAt(0);
			
			}catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
				//error message to user
		
			} catch (IOException e1) {
			
				e1.printStackTrace();
				//error message to user
			}
			
			Iterator<Row> rowIterator = Sheet.iterator();
			
			String className = "";
			
			rowIterator.next();
			
			while (rowIterator.hasNext()){
				
				Row row = rowIterator.next();
				
				if(!row.getCell(2).getStringCellValue().equals(className)) {
					
					className = row.getCell(2).getStringCellValue() ;
					
					if(row.getCell(7).getBooleanCellValue() == true) {
						
						Text tex = new Text();
						tex.setText( "Is_God_Class found at Class name = " + className);
						tex.setFill(Color.RED);
						report.getChildren().add(tex);
						
					}else{
						
						Text tex = new Text();
						tex.setText( "No code smells detected in class " + className);
						tex.setFill(Color.GREEN);
						report.getChildren().add(tex);
						
					}
					
				}
				
				if(row.getCell(10).getBooleanCellValue() == true) {
					
					Text tex = new Text();
					tex.setText( "Is_Long_Method found at MethodID = " + row.getCell(0).getNumericCellValue());
					tex.setFill(Color.RED);
					report.getChildren().add(tex);
					
				}else {
					
					Text tex = new Text();
					tex.setText( "No code smells found at MethodID = " + row.getCell(0).getNumericCellValue());
					tex.setFill(Color.GREEN);
					report.getChildren().add(tex);
					
				}
				
			}
			
			Text tex = new Text();
			tex.setText("========================================DONE===============================================");
			report.getChildren().add(tex);
			
		});
		
		Scene scene = new Scene(pane, 1920, 1000);
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
	
		
	}
	
}
