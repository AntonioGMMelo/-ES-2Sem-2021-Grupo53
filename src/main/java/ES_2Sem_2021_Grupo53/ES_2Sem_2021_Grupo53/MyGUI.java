package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MyGUI extends Application {

	ArrayList<String> methodOrder = new ArrayList<String>();
	ArrayList<String> classOrder = new ArrayList<String>();
	ArrayList<String> methodLogic = new ArrayList<String>();
	ArrayList<String> classLogic = new ArrayList<String>();
	ArrayList<Integer> methodThreshold = new ArrayList<Integer>();
	ArrayList<Integer> classThreshold = new ArrayList<Integer>();
	
	public String[] remove(String[] array, String item) {
		
		String [] answer = new String[array.length -1];
		int answerIndex = 0;
		
		for(int i = 0; i < array.length; i++)
			if(!array[i].equals(item)) {
				
				answer[answerIndex] = array[i];
				answerIndex ++;
				
			}
			
		return answer;
		
	}
	
	
	@Override
	public void start(final Stage primaryStage) throws Exception {

		primaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream("icon.png")));
		primaryStage.setTitle("Code Smells");
		
		//First smell definition
		final GridPane gridHelper = new GridPane();
		
		String[] smells = new String[] {"is_God_CLass", "is_Long_Method"};
		final ComboBox smell = new ComboBox(FXCollections.observableArrayList(smells));
		
		gridHelper.add(smell, 0, 0);
		
		final String[] methodsMetricHelper = new String[] {"LOC_Method", "CYCLO_Method"};
		final String[] classMetricsHelper = new String[] {"LOC_Class", "NOM_Class", "WMC_Class"};	
		
		smell.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				String[] methodsMetrics = methodsMetricHelper;
				String[] classMetrics = classMetricsHelper;
				GridPane grid = gridHelper;
				
				if(newValue.toString().equals("is_God_Class")) {
					
					ComboBox metrics = new ComboBox(FXCollections.observableArrayList(newValue.toString().equals("is_God_Class")));
				
					grid.add(metrics, 2, 0);
				
					if(metrics.getValue().equals("LOC_Class")) {
					
						metrics.setDisable(true);
						classOrder.add("LOC_Class");
						classMetrics = remove(classMetrics,"LOC_Class");
					
					}else if(metrics.getValue() == "NOM_Class") {
						
						metrics.setDisable(true);
						classOrder.add("NOM_Class");
						classMetrics = remove(classMetrics,"NOM_Class");	
					
					}else if(metrics.getValue().equals("WMC_Class")) {
					
						metrics.setDisable(true);
						classOrder.add("WMC_Class");
						classMetrics = remove(classMetrics,"WMC_Class");
					
					}
				
					TextField threshold1 = new TextField();
					threshold1.setPromptText("Threshold");
				
					try {
					
						int helper = Integer.parseInt(threshold1.getText());
						threshold1.setDisable(true);
						classThreshold.add(helper);
					
					}catch(Exception e) {
						//Error Message pop up to do
					}
				
					if(threshold1.isDisabled()) {
					
						ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));
				
						if(logic.getValue().toString().equals("AND")) {
					
							logic.setDisable(true);
							classLogic.add("AND");
					
						}else if(logic.getValue().toString().equals("OR")) {
					
							logic.setDisable(true);
							classLogic.add("OR");
					
						}
					
					}
				
				}else if(smell.getValue().toString().equals("is_Long_Method")) {
				
					ComboBox metrics = new ComboBox(FXCollections.observableArrayList(smell.getValue().toString().equals("is_Long_Method")));
				
					grid.add(metrics, 2, 0);
				
					if(metrics.getValue().equals("LOC_Method")) {
					
						metrics.setDisable(true);
						methodOrder.add("LOC_Method");
						methodsMetrics = remove(methodsMetrics,"LOC_Method");
					
					}else if(metrics.getValue().equals("CYCLO_Method")) {
						
						metrics.setDisable(true);
						classOrder.add("CYCLO_Method");
						methodsMetrics = remove(methodsMetrics,"CYCLO_Method");	
					
					}
				
				}
				
				Scene scene = new Scene(gridHelper, 300, 250);
				primaryStage.setScene(scene);	
				
			}}); 

			
		Scene scene = new Scene(gridHelper, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		return ;
		
	}

			
	public static void main(String[] args) {
		launch(args);
	}
		
}
