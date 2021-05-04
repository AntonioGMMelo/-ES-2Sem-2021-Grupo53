package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

public class Assert {

	@Test
	public void testAssertion(){
		
		String PATH = ".\\bin\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\jasml_0.10\\src";
	
		//isCodeSmell (2 or more metrics)
		ArrayList<Integer> allMetricsExample = new ArrayList<Integer>();
		allMetricsExample.add(10);
		allMetricsExample.add(15);
		allMetricsExample.add(20);
		
		ArrayList<Integer> thresholdsExample = new ArrayList<Integer>();
		thresholdsExample.add(10);
		thresholdsExample.add(14);
		thresholdsExample.add(21);
		
		ArrayList<String> logicOpExample = new ArrayList<String>();
		logicOpExample.add("AND");
		logicOpExample.add("OR");
		
		boolean answer2 = new Metrics().isCodeSmell(allMetricsExample, logicOpExample, thresholdsExample);
		
		assertFalse(answer2);
		
		//isCodeSmell(1 metric)
		
		ArrayList<Integer> allMetricsExample2 = new ArrayList<Integer>();
		allMetricsExample2.add(50);
//		System.out.println(allMetricsExample2);
				
		ArrayList<Integer> thresholdsExample2 = new ArrayList<Integer>();
		thresholdsExample2.add(52);
//		System.out.println(thresholdsExample2.get(0));
				
		ArrayList<String> logicOpExample2 = new ArrayList<String>();
//		System.out.println(logicOpExample2.size());
//		System.out.println(logicOpExample2.isEmpty());
		
		boolean answer3 = new Metrics().isCodeSmell(allMetricsExample2, logicOpExample2, thresholdsExample2);
		
		assertFalse(answer3);
		
		//For getMetrics
		ArrayList<String> orderOfMethods = new ArrayList<String>();
		orderOfMethods.add("LOC_Method");
		orderOfMethods.add("CYCLO_Method");
		
		ArrayList<String> logicMethods = new ArrayList<String>();
		logicMethods.add("AND");
		
		ArrayList<Integer> thresholdsMethods= new ArrayList<Integer>();
		thresholdsMethods.add(4);
		thresholdsMethods.add(2);
		
		ArrayList<String> orderOfClass = new ArrayList<String>();
		orderOfClass.add("WMC_Class");
		orderOfClass.add("NOM_Class");
		orderOfClass.add("LOC_Class");
		
		ArrayList<String> logicClass = new ArrayList<String>();
		logicClass.add("OR");
		logicClass.add("OR");
		
		ArrayList<Integer> thresholdsClass = new ArrayList<Integer>();
		thresholdsClass.add(10000);
		thresholdsClass.add(1000);
		thresholdsClass.add(10);
		
		int[] answer1 = new Metrics().getMetrics(PATH, orderOfMethods, logicMethods, thresholdsMethods, orderOfClass, logicClass, thresholdsClass); 

		assertArrayEquals(new int[] {4,42,247,5217}, answer1);
		
		//getClassName
		
		String result1 = new Metrics().getClassName("private static class className{");
		String result2 = new Metrics().getClassName("private class className{");
		String result3 = new Metrics().getClassName("private static class className {");
		String result4 = new Metrics().getClassName("private static class className extends some_class {");
		String result5 = new Metrics().getClassName("private static class className implements some_interface {");
		
		assertEquals("className", result1);
		assertEquals("className", result2);
		assertEquals("className", result3);
		assertEquals("className", result4);
		assertEquals("className", result5);
		
		// isMethod
		
		boolean result6 = new Metrics().isMethod("private static void methodName(){");
		boolean result7 = new Metrics().isMethod("private int methodName(int a, int b){");
		boolean result8 = new Metrics().isMethod("static char methodName() {");
		boolean result9 = new Metrics().isMethod("String[] methodName(){");
		boolean result10 = new Metrics().isMethod("ArrayList<char> methodName() {");
		boolean result11 = new Metrics().isMethod("private static class implements some_interface className {");
		boolean result12 = new Metrics().isMethod("for(int i ; i <  l ; i++)");
		
		assertTrue(result6);
		assertTrue(result7);
		assertTrue(result8);
		assertTrue(result9);
		assertTrue(result10);
		assertFalse(result11);
		assertFalse(result12);
		
		//getMethodName
		
		String result13 = new Metrics().getMethodName("private static void methodName() {");
		String result14 = new Metrics().getMethodName("private int methodName(int a, int b){");
		String result15 = new Metrics().getMethodName("public String methodName(int c) {");
		
		assertEquals("methodName", result13);
		assertEquals("methodName", result14);
		assertEquals("methodName", result15);
		
		File theoretical = new File(".\\Dummy_metricsTest.xlsx");
		File practical = new File(".\\Dummy_metrics.xlsx");
		
		int[] result16 = new Metrics().compare(theoretical, practical);
		
		assertArrayEquals(new int[] {8,4,0,0}, result16);
		
	}
	
}
