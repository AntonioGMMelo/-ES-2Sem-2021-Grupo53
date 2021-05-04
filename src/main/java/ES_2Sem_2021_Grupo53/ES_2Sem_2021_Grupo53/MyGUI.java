package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyGUI extends Application {

	ArrayList<String> methodOrder = new ArrayList<String>();
	ArrayList<String> classOrder = new ArrayList<String>();
	ArrayList<String> methodLogic = new ArrayList<String>();
	ArrayList<String> classLogic = new ArrayList<String>();
	ArrayList<Integer> methodThreshold = new ArrayList<Integer>();
	ArrayList<Integer> classThreshold = new ArrayList<Integer>();
	
	
	/**
	 * Simple method to remove an item from an array
	 * 
	 * Goes trough the array copying it to an array with 1 less length copies every position
	 * except for the one where the element in that position is equal to the item.
	 * 
	 * @param array, item
	 * @return array without the item
	 */
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
	
	/**
	 * Simple helper method to add something to the end of an array
	 * 
	 * Copies the array to another array that has 1 more length and adds the item to the last
	 * position.
	 * 
	 * @param array, item
	 * @return array with the item at the end
	 */
	public String[] add(String[] array, String item) {
		
		String[] answer = new String[array.length +1];
		
		for(int i = 0; i < array.length; i++) answer[i] = array[i];
		
		answer[answer.length - 1] = item;
		
		return answer;
		
	}
	
	/**
	 * This method shows the rule creation page of the interface
	 * 
	 * Shows the first combo box and after the user selects one of the code smells
	 * show another combo box with the metrics that can be used for that code smell after one of the metrics is selected displays
	 * a text field to add a threshold after the text field is selected displays a combo box with the logic operators AND and OR, if one
	 * is selected show another combo box with the remaining metrics and it goes on like this until there are no more metrics to build the rule with,
	 * a combo box with the other code smell and follows the same path previously described (metrics-> text field-> logic...)
	 *  and a test rules button which calls getMetrics() and displays the CalibratePopUp.
	 * 
	 */
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
								
								classMetrics = remove(classMetrics,"LOC_Class");
								
								if(classOrder.size()>0) classOrder.remove(0);
								
								classOrder.add(0, "LOC_Class");
								
							}else if(newValue.equals("NOM_Class")) {
								
								classMetrics = remove(classMetrics,"NOM_Class");
								
								if(classOrder.size() > 0) classOrder.remove(0);
								
								classOrder.add(0, "NOM_Class");
								
							
							}else if(newValue.equals("WMC_Class")) {
							
								classMetrics = remove(classMetrics,"WMC_Class");
							
								if(classOrder.size()>0) classOrder.remove(0);
								
								classOrder.add(0 ,"WMC_Class");
								
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

													if(classLogic.size() > 0) classLogic.remove(0);
													
													classLogic.add(0, "AND");

												}else if(newValue.toString().equals("OR")) {

													if(classLogic.size() > 0) classLogic.remove(0);
													
													classLogic.add(0,"OR");

												}
												
												ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(classMetrics));
												
												grid.add(metric2, 3, 2);
												
												metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

													@Override
													public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
														
														for(int i = 0; i < classMetrics.length; i++) {
													
															if(newValue.toString().equals(classMetrics[i])) {
																
																if(classOrder.size() > 1) classOrder.remove(1);
																classOrder.add(1, newValue.toString());
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
																				
																				if(classLogic.size() > 1) classLogic.remove(1);
																				classLogic.add(1, "AND");

																			}else if(newValue.toString().equals("OR")) {

																				if(classLogic.size() > 1) classLogic.remove(1);
																				classLogic.add(1, "OR");

																			}
																			
																			ComboBox metric3 = new ComboBox(FXCollections.observableArrayList(classMetrics));
																			
																			grid.add(metric3, 6, 2);
													
																			metric3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																				@Override
																				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
																					
																					if(newValue.toString().equals(classMetrics[0])) {
																						
																						if(classOrder.size() >2) classOrder.remove(2);
																						classOrder.add(2,newValue.toString());
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
																									if(classThreshold.size() > 2) classThreshold.remove(2);
																									classThreshold.add(2, helper);

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
																		if(classThreshold.size() > 1) classThreshold.remove(1);
																		classThreshold.add(1, helper);

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
											if(classThreshold.size() > 0) classThreshold.remove(0);
											classThreshold.add(0,helper);

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
											
											if(methodOrder.size() > 0) methodOrder.remove(0);
											methodOrder.add(0, newValue.toString());
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

															if(methodLogic.size() > 0) methodLogic.remove(0);
															methodLogic.add(0, "AND");

														}else if(newValue.toString().equals("OR")) {

															if(methodLogic.size() > 0) methodLogic.remove(0);
															methodLogic.add(0, "OR");

														}
														
														ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(methodsMetrics));
														
														grid.add(metric2, 3, 5);
														
														metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

															@Override
															public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
																
																if(methodOrder.size() > 1) methodOrder.remove(1);
																methodOrder.add(1, methodsMetrics[0]);
																
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
																				if(methodThreshold.size() > 1) methodThreshold.remove(1);
																				methodThreshold.add(1, helper);

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
													if(methodThreshold.size() > 0) methodThreshold.remove(0);
													methodThreshold.add(0, helper);

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

					String[] smells = smellsHelper;
					String[] smells2 = remove(smells, "is_Long_Method");

					ComboBox smell2 = new ComboBox(FXCollections.observableArrayList(smells2));

					grid.add(smell2, 0, 3);

					smell2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

							final String[] classMetricsHelper2 = classMetricsHelper;
							final GridPane grid = gridHelper;

							if(newValue.toString().equals("is_God_Class")) {

								String[] smells = smellsHelper;
								smells = remove(smells, "is_God_Class");
								final String[] smells2 = smells;

								final ComboBox metricsHelper = new ComboBox(FXCollections.observableArrayList(classMetricsHelper2));

								grid.add(metricsHelper, 0, 4);

								metricsHelper.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

									@Override
									public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

										String[] classMetrics = classMetricsHelper2;
										ComboBox metrics = metricsHelper;

										if(newValue.equals("LOC_Class")) {

											if(classOrder.size() > 0) classOrder.remove(0);
											classOrder.add(0, "LOC_Class");
											classMetrics = remove(classMetrics,"LOC_Class");

										}else if(newValue.equals("NOM_Class")) {

											if(classOrder.size() > 0) classOrder.remove(0);
											classOrder.add(0, "NOM_Class");
											classMetrics = remove(classMetrics,"NOM_Class");	

										}else if(newValue.equals("WMC_Class")) {

											if(classOrder.size() > 0) classOrder.remove(0);
											classOrder.add(0, "WMC_Class");
											classMetrics = remove(classMetrics,"WMC_Class");

										}

										final TextField threshold1 = new TextField();
										threshold1.setPromptText("Threshold");
										grid.add(threshold1, 1, 4);
										final String[] classMetricsHelper = classMetrics;

										threshold1.focusedProperty().addListener(new ChangeListener<Boolean>() {

											@Override
											public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

												if(newValue){

													ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

													grid.add(logic, 2, 4);

													logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

														String[] classMetrics = classMetricsHelper;

														@Override
														public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

															if(newValue.toString().equals("AND")) {

																if(classLogic.size() > 0) classLogic.remove(0);
																classLogic.add(0, "AND");

															}else if(newValue.toString().equals("OR")) {

																if(classLogic.size() > 0) classLogic.remove(0);
																classLogic.add(0, "OR");
													
															}

															ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(classMetrics));

															grid.add(metric2, 3, 4);

															metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																@Override
																public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

																	for(int i = 0; i < classMetrics.length; i++) {

																		if(newValue.toString().equals(classMetrics[i])) {

																			if(classOrder.size() > 1) classOrder.remove(1);
																			classOrder.add(1, newValue.toString());
																			classMetrics = remove(classMetrics, classMetrics[i]);
																			break;

																		}

																	}

																	TextField threshold2 = new TextField();
																	threshold2.setPromptText("Threshold2");
																	grid.add(threshold2, 4, 4);
																	final TextField threshold = threshold2;
																	final String[] classMetricsHelper = classMetrics;

																	threshold2.focusedProperty().addListener(new ChangeListener<Boolean>() {

																		@Override
																		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

																			if(newValue){

																				ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

																				grid.add(logic, 5, 4);

																				logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																					String[] classMetrics = classMetricsHelper;

																					@Override
																					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

																						if(newValue.toString().equals("AND")) {

																							if(classLogic.size() > 1) classLogic.remove(1);
																							classLogic.add(1, "AND");

																						}else if(newValue.toString().equals("OR")) {

																							if(classLogic.size() > 1) classLogic.remove(1);
																							classLogic.add(1, "OR");

																						}

																						ComboBox metric3 = new ComboBox(FXCollections.observableArrayList(classMetrics));

																						grid.add(metric3, 6, 4);

																						metric3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

																							@Override
																							public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

																								if(newValue.toString().equals(classMetrics[0])) {

																									if(classOrder.size() > 2) classOrder.remove(2);
																									classOrder.add(2, newValue.toString());
																									classMetrics = remove(classMetrics, newValue.toString());

																								}

																								final TextField threshold3 = new TextField();
																								threshold3.setPromptText("Threshold3");
																								grid.add(threshold3, 7, 4);

																								threshold3.focusedProperty().addListener(new ChangeListener<Boolean>() {

																									@Override
																									public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

																										if(!newValue) {

																											TextField threshold = threshold3;

																											try {

																												int helper = Integer.parseInt(threshold.getText());
																												if(classThreshold.size() > 2) classThreshold.remove(2);
																												classThreshold.add(2, helper);

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
																					if(classThreshold.size() > 1) classThreshold.remove(1);
																					classThreshold.add(1, helper);

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
														if(classThreshold.size() > 0) classThreshold.remove(0); 
														classThreshold.add(0, helper);

													}catch(Exception e) {

														System.out.println("fucked it m8");

													}

												}

											}

										});
									}
									
								});
								
							}
							
						}
					
					});

					ComboBox metricsHelper = new ComboBox(FXCollections.observableArrayList(methodsMetricHelper));

					grid.add(metricsHelper,0,1);

					metricsHelper.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

							String[] methodsMetrics = methodsMetricHelper;

							for(int i = 0; i < methodsMetrics.length; i++) {

								if(newValue.toString().equals(methodsMetrics[i])) {

									if(methodOrder.size() > 0) methodOrder.remove(0);
									methodOrder.add(0, newValue.toString());
									methodsMetrics = remove(methodsMetrics, methodsMetrics[i]);
									break;

								}

							}

							final String[] methodsMetrics2 = methodsMetrics;
							final TextField threshold1 = new TextField();
							threshold1.setPromptText("Threshold");
							grid.add(threshold1, 1, 1);

							threshold1.focusedProperty().addListener(new ChangeListener<Boolean>() {

								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

									if(newValue){

										ComboBox logic = new ComboBox(FXCollections.observableArrayList(new String[]{"AND", "OR"}));

										grid.add(logic, 2, 1);

										logic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

											@Override
											public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

												final String[] methodsMetrics = methodsMetrics2;

												if(newValue.toString().equals("AND")) {

													if(methodLogic.size() > 0) methodLogic.remove(0);
													methodLogic.add(0, "AND");

												}else if(newValue.toString().equals("OR")) {

													if(methodLogic.size() > 0) methodLogic.remove(0);
													methodLogic.add(0, "OR");

												}

												ComboBox metric2 = new ComboBox(FXCollections.observableArrayList(methodsMetrics));

												grid.add(metric2, 3, 1);

												metric2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

													@Override
													public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

														if(methodOrder.size() > 1) methodOrder.remove(1);
														methodOrder.add(1, methodsMetrics[0]);

														final TextField threshold2 = new TextField();
														threshold1.setPromptText("Threshold2");
														grid.add(threshold2, 4, 1);

														threshold2.focusedProperty().addListener(new ChangeListener<Boolean>() {

															@Override
															public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

																if(!newValue) {

																	TextField threshold = threshold2;

																	try {

																		int helper = Integer.parseInt(threshold.getText());
																		if(methodThreshold.size() > 1) methodThreshold.remove(1);
																		methodThreshold.add(1, helper);

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
											if(methodThreshold.size() > 0) methodThreshold.remove(0);
											methodThreshold.add(0, helper);

										}catch(Exception e) {

											System.out.println("fucked it m8");

										}

									}


								}

							});

						}

					});
						
				}
				
				Button test = new Button("Test Rules");
				grid.add(test, 0, 6);
				
				final Text t1 = new Text();
				t1.setWrappingWidth(250);
		    	grid.add(t1, 0, 10);
		    	
				test.setOnAction(new EventHandler<ActionEvent>() {
					
				    @Override 
				    public void handle(ActionEvent e) {
				    
				    	System.out.println(classOrder);	
				    	System.out.println(methodOrder);
				    	System.out.println(classLogic);
				    	System.out.println(methodLogic);
				    	System.out.println(classThreshold);
				    	System.out.println(methodThreshold);

				    	new Metrics().getMetrics(".\\bin\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\jasml_0.10\\src", methodOrder, methodLogic, methodThreshold, classOrder, classLogic, classThreshold);
				    	int[] numbers = new Metrics().compare(new File(".\\Code_Smells.xlsx"), new File(".\\src_metrics.xlsx"));
				    
				    	System.out.println(numbers[0]);
				    	System.out.println(numbers[1]);
				    	System.out.println(numbers[2]);
				    	System.out.println(numbers[3]);
				    	
				    	String s = CalibratePopUp.display(numbers);
				    	
				    	if(s.equals("MainMenu")) {
				    		
				    		//Write rule to file

				    		primaryStage.close();
				    		MainMenu.display(methodOrder, methodLogic, methodThreshold, classOrder, classLogic, classThreshold);
				    	
				    	}
				    	
				    	Text t = t1;
				    	
				    	t.setText(s);
				    	
				    	
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
