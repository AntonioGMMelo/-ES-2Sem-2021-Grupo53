package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;


import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;

public class Main {
	
	//Main Class
	public static void main( String[] args ){
		
		Result result = JUnitCore.runClasses(Assert.class);
		
		for(Failure failure : result.getFailures()) {
			
			System.out.println(failure.toString());
			
		}
		
		System.out.println("Succesful: " + result.wasSuccessful());
		
	}

}
