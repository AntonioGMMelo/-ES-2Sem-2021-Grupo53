package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assert {

	@Test
	public void testAssertion(){
		
<<<<<<< HEAD
		String PATH = "C:\\Users\\migue\\eclipse-workspace\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass.java";		
		String PATH2 = "C:\\Users\\migue\\eclipse-workspace\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass2.java";

		int answer1 = new Metrics().getMetrics(PATH); 
		int answer2 = new Metrics().getMetrics(PATH2);
					
		assertEquals(20, answer1);
		assertEquals(44, answer2);
=======
		String PATH = "C:\\Users\\Ant\\eclipse-workspace\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy";
		
		//String PATH ="C:\\Users\\Maria\\Desktop\\3ºAno\\Engenharia de Software\\Projeto\\ES\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass.java";
		//String PATH2 ="C:\\Users\\Maria\\Desktop\\3ºAno\\Engenharia de Software\\Projeto\\ES\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass2.java";
		
		//For LOC_CLass
		int[] answer1 = new Metrics().getMetrics(PATH);

		int[] expected1 = new int[2];
		expected1[0] = 20;
		expected1[1] = 4;
		
		assertArrayEquals(expected1, answer1);
		
		//getClassName
		
		String result1 = new Metrics().getClassName("private static class className{");
		String result2 = new Metrics().getClassName("private class className{");
		String result3 = new Metrics().getClassName("private static class className {");
		String result4 = new Metrics().getClassName("private static class extends some_class className{");
		String result5 = new Metrics().getClassName("private static class implements some_interface className {");
		
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
>>>>>>> 6f5b79ffd5211e8f3c86363b4cd3bea135591731
		
	}
	
}
