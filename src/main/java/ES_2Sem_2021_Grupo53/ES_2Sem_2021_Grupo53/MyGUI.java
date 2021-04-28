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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(final Stage primaryStage) throws Exception {

		primaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream("icon.png")));
		primaryStage.setTitle("Code Smells");
		
		//First smell definition
		final GridPane gridHelper = new GridPane();
		
		String[] smells = new String[] {"is_God_Class", "is_Long_Method"};
		final ComboBox smell = new ComboBox(FXCollections.observableArrayList(smells));
		
		gridHelper.add(smell, 0, 0);
		
		final String[] methodsMetricHelper = new String[] {"LOC_Method", "CYCLO_Method"};
		final String[] classMetricsHelper = new String[] {"LOC_Class", "NOM_Class", "WMC_Class"};	
		
		smell.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			
				final String[] classMetricsHelper2 = classMetricsHelper;
				final GridPane grid = gridHelper;
				
				if(newValue.toString().equals("is_God_Class")) {
					
					final ComboBox metricsHelper = new ComboBox(FXCollections.observableArrayList(classMetricsHelper2));
				
					grid.add(metricsHelper, 0, 2);
				
					metricsHelper.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						
							 String[] classMetrics = classMetricsHelper2;
							 ComboBox metrics = metricsHelper;
							 
							if(newValue.equals("LOC_Class")) {
			
								classOrder.add("LOC_Class");
								classMetrics = remove(classMetrics,"LOC_Class");
							
							}else if(newValue.equals("NOM_Class")) {
								
								classOrder.add("NOM_Class");
								classMetrics = remove(classMetrics,"NOM_Class");	
							
							}else if(newValue.equals("WMC_Class")) {
							
								classOrder.add("WMC_Class");
								classMetrics = remove(classMetrics,"WMC_Class");
							
							}
						
							TextField threshold1 = new TextField();
							threshold1.setPromptText("Threshold");
							grid.add(threshold1, 1, 2);
						
							//try {
							
								//int helper = Integer.parseInt(threshold1.getText());
								//threshold1.setDisable(true);
								//classThreshold.add(helper);
							
						//	}catch(Exception e) {
								//Error Message pop up to do
							//}
						
							//if(threshold1.isDisabled()) {
							
								//ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));
						
								//if(logic.getValue().toString().equals("AND")) {
							
									//logic.setDisable(true);
									//classLogic.add("AND");
							
								//}else if(logic.getValue().toString().equals("OR")) {
							
									//logic.setDisable(true);
									//classLogic.add("OR");
							
								//}
							
							//}
							
						}
					
					});
				
				}else if(smell.getValue().toString().equals("is_Long_Method")) {
				
					final String[] methodsMetricsHelper2 = methodsMetricHelper;
					
					final ComboBox metricsHelper = new ComboBox(FXCollections.observableArrayList(methodsMetricsHelper2));
				
					grid.add(metricsHelper, 0, 2);
				

					metricsHelper.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

						ComboBox metrics = metricsHelper;
						
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						
							String[] methodsMetrics = methodsMetricsHelper2;
							
							if(newValue.equals("LOC_Method")) {
							
								methodOrder.add("LOC_Method");
								methodsMetrics = remove(methodsMetrics,"LOC_Method");
							
							}else if(newValue.equals("CYCLO_Method")) {
								
								classOrder.add("CYCLO_Method");
								methodsMetrics = remove(methodsMetrics,"CYCLO_Method");	
							
							}
							
						} 
						
					});

				
				}
				
				Scene scene2 = new Scene(gridHelper, 300, 250);
				primaryStage.setScene(scene2);
				
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
