package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Assert {

	@Test
	public void testAssertion(){
		
		String PATH = "C:\\Users\\migue\\eclipse-workspace\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass.java";		
		String PATH2 = "C:\\Users\\migue\\eclipse-workspace\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass2.java";
		
		int answer1 = new Metrics().getMetrics(PATH); 
		int answer2 = new Metrics().getMetrics(PATH2);
		
		assertEquals(answer1, 20);
		assertEquals(answer2, 44);
		
	}
	
}
