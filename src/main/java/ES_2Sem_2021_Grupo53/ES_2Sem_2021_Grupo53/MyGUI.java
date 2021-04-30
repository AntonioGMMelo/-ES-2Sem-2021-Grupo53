package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
		final String[] smellsHelper = smells;
		
		smell.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			
				final String[] classMetricsHelper2 = classMetricsHelper;
				final GridPane grid = gridHelper;
				
				if(newValue.toString().equals("is_God_Class")) {
					
					String[] smells = smellsHelper;
					smells = remove(smells, "is_God_Class");
					final String[] smells2 = smells;
					
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
						
							final TextField threshold1 = new TextField();
							threshold1.setPromptText("Threshold");
							grid.add(threshold1, 1, 2);
							final String[] classMetricsHelper = classMetrics;
							
							threshold1.focusedProperty().addListener(new ChangeListener<Boolean>() {

								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
									
									if(newValue){
										
										ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

										grid.add(logic, 2, 2);
										
										logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

											String[] classMetrics = classMetricsHelper;
											
											@Override
											public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
												
												if(newValue.toString().equals("AND")) {

													classLogic.add("AND");

												}else if(newValue.toString().equals("OR")) {

													classLogic.add("OR");

												}
												
												ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(classMetrics));
												
												grid.add(metric2, 3, 2);
												
												metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

													@Override
													public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
														
														for(int i = 0; i < classMetrics.length; i++) {
													
															if(newValue.toString().equals(classMetrics[i])) {
																
																classOrder.add(newValue.toString());
																classMetrics = remove(classMetrics, classMetrics[i]);
																break;
																
															}
															
														}
														
														TextField threshold2 = new TextField();
														threshold2.setPromptText("Threshold2");
														grid.add(threshold2,4, 2);
														final TextField threshold = threshold2;
														final String[] classMetricsHelper = classMetrics;
													
														threshold2.focusedProperty().addListener(new ChangeListener<Boolean>() {

															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																
																if(newValue){
																	
																	ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

																	grid.add(logic, 5, 2);
																	
																	logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																		String[] classMetrics = classMetricsHelper;
																		
																		@Override
																		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
																			
																			if(newValue.toString().equals("AND")) {

																				classLogic.add("AND");

																			}else if(newValue.toString().equals("OR")) {

																				classLogic.add("OR");

																			}
																			
																			ComboBox metric3 = new ComboBox(FXCollections.observableArrayList(classMetrics));
																			
																			grid.add(metric3, 6, 2);
													
																			metric3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																				@Override
																				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
																					
																					if(newValue.toString().equals(classMetrics[0])) {
																						
																						classOrder.add(newValue.toString());
																						classMetrics = remove(classMetrics, newValue.toString());
																						
																					}
																					
																					final TextField threshold3 = new TextField();
																					threshold3.setPromptText("Threshold3");
																					grid.add(threshold3, 7, 2);
																					
																					threshold3.focusedProperty().addListener(new ChangeListener<Boolean>() {

																						@Override
																						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																							
																							if(!newValue) {
																								
																								TextField threshold = threshold3;
																								
																								try {

																									int helper = Integer.parseInt(threshold.getText());
																									classThreshold.add(helper);

																								}catch(Exception e) {
																								
																									System.out.println("fucked it m8");
																								
																								}

																							}
																							
																						}																						
																						
																					});
																						
																				}
																				
																			});
																			
																		}
																		
																	});
																}
																
																if(!newValue) {
																	
																	TextField threshold2 = threshold;
																	
																	try {

																		int helper = Integer.parseInt(threshold2.getText());
																		classThreshold.add(helper);

																	}catch(Exception e) {
																	
																		System.out.println("fucked it m8");
																	
																	}

																}
																
															}															
													
														});														
														
													}
												
												});
											
											}	
										
										});

									}
									
									if(!newValue) {
										
										TextField threshold2 = threshold1;
										
										try {

											int helper = Integer.parseInt(threshold2.getText());
											classThreshold.add(helper);

										}catch(Exception e) {
										
											System.out.println("fucked it m8");
										
										}

									}

								}
							
							});
						
							
							
						}
						
					});
					
					ComboBox smell2 = new ComboBox(FXCollections.observableArrayList(smells2));
					
					grid.add(smell2, 0, 4);
					
					smell2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							
							ComboBox metricsHelper = new ComboBox(FXCollections.observableArrayList(methodsMetricHelper));
							
							grid.add(metricsHelper,0,5);
							
							metricsHelper.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

								@Override
								public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
									
									String[] methodsMetrics = methodsMetricHelper;
									
									for(int i = 0; i < methodsMetrics.length; i++) {
								
										if(newValue.toString().equals(methodsMetrics[i])) {
											
											methodOrder.add(newValue.toString());
											methodsMetrics = remove(methodsMetrics, methodsMetrics[i]);
											break;
											
										}
										
									}
									
									final String[] methodsMetrics2 = methodsMetrics;
									final TextField threshold1 = new TextField();
									threshold1.setPromptText("Threshold");
									grid.add(threshold1, 1, 5);
									
									threshold1.focusedProperty().addListener(new ChangeListener<Boolean>() {

										@Override
										public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
											
											if(newValue){
												
												ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

												grid.add(logic, 2, 5);
												
												logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
													
													@Override
													public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

														final String[] methodsMetrics = methodsMetrics2;
														
														if(newValue.toString().equals("AND")) {

															methodLogic.add("AND");

														}else if(newValue.toString().equals("OR")) {

															methodLogic.add("OR");

														}
														
														ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(methodsMetrics));
														
														grid.add(metric2, 3, 5);
														
														metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

															@Override
															public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
																
																methodOrder.add(methodsMetrics[0]);
																
																final TextField threshold2 = new TextField();
																threshold1.setPromptText("Threshold2");
																grid.add(threshold2, 4, 5);
																
																threshold2.focusedProperty().addListener(new ChangeListener<Boolean>() {

																	@Override
																	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
																		
																		if(!newValue) {
																			
																			TextField threshold = threshold2;
																			
																			try {

																				int helper = Integer.parseInt(threshold.getText());
																				methodThreshold.add(helper);

																			}catch(Exception e) {
																			
																				System.out.println("fucked it m8");
																			
																			}

																		}
																		
																	}
																	
																});
																																
															}
														
														});
													}
												});
											}
														
											
											if(!newValue) {
												
												TextField threshold2 = threshold1;
												
												try {

													int helper = Integer.parseInt(threshold2.getText());
													methodThreshold.add(helper);

												}catch(Exception e) {
												
													System.out.println("fucked it m8");
												
												}

											}
											
											
										}
										
									});
									
								}
								
							});
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
				
				Button test = new Button("Test Rules");
				grid.add(test, 0, 6);
				
				test.setOnAction(new EventHandler<ActionEvent>() {
					
				    @Override 
				    public void handle(ActionEvent e) {
				    
				    	System.out.println(classOrder);	
				    	System.out.println(methodOrder);
				    	System.out.println(classLogic);
				    	System.out.println(methodLogic);
				    	System.out.println(classThreshold);
				    	System.out.println(methodThreshold);

				    }
				    
				});
				
			}}); 
			
		Scene scene = new Scene(gridHelper, 1920, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		return ;
		
	}

			
	public static void main(String[] args) {
		launch(args);
	}
		
}
