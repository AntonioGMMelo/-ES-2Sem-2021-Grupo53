package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53.Dummy;

//Dummy Class for Testing Complexity (Cyclomatic Complexity) 
public class DummyClass {

	//Constant Operation O(1) Complexity
	private static void noLoop() {

		System.out.println("Simple Complexity");

	}

	//Single Loop Operation O(n) Complexity
	private static void oneLoop(){

		for(int index = 0; index < 30; index++)

			System.out.println(index);

	}

	//Double Nested Loop Operation O(n^2) Complexity
	private static void twoLoop(){
		
		for(int index = 0; index < 30; index++) 
			for(int indexj = 0; indexj < 30; indexj++) 

				System.out.println(indexj);

	}

	//Triple Nested Loop Operation O(n^3) Complexity
	private static void threeLoop(){

		for(int index = 0; index < 30; index++) 
			for(int indexj = 0; indexj < 30; indexj++) 
				for(int indexk = 0; indexk < 30; indexk++) 

					System.out.println(indexk);

	}

}