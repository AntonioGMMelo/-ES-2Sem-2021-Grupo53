package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;

//Metric Extraction File
public class Metrics {
    
	private int numberOfPackets = 0; //Increment every time we enter a new folder
	private int numberOfClasses = 0; //Increment every time we enter a new file
	private int numberOfMethods = 0;//Increment every time we enter a new method
	private int numberOfLines = 0;//Increment every time we enter a new line
	public static final String PATH = "./Dummy"; //Replace with user specified path when GUI is operational
	
	private boolean getMetrics(String Path) {
		
	//1-Open files from path
		//If path is empty throw exception(Return false) failure
	//2-Loop to parse each packages
		setNumberOfPackets(getNumberOfPackets() + 1);
	//3-Loop to parse each file in the packages
		int numberOfLinesClass = 0; // Increment every new line
		ArrayList<Integer> methodsCyclomatic = new ArrayList<Integer>();
		//If new Method
			int numberOfLinesMethod = 0;//Increment every new line till end of method
			int numberOfLoops = 0;//Increment every time there is a for or while loop in the code
			setNumberOfMethods(getNumberOfMethods() + 1);
			methodsCyclomatic.add(numberOfLoops);
			//Print method info to xlsx file
		int classComplexity = 0; //Set to max of array of methodsCyclomatic 
		setNumberOfClasses(getNumberOfClasses() + 1);
		setNumberOfLines(getNumberOfLines() + numberOfLinesClass);
		//Print class info to x
		return true; // succes
	}
		
	public int getNumberOfPackets() {
	
		return numberOfPackets;
	
	}

	public void setNumberOfPackets(int numberOfPackets) {
	
		this.numberOfPackets = numberOfPackets;
	
	}

	public int getNumberOfClasses() {
	
		return numberOfClasses;
	
	}

	public void setNumberOfClasses(int numberOfClasses) {
	
		this.numberOfClasses = numberOfClasses;
	
	}

	public int getNumberOfMethods() {
	
		return numberOfMethods;
	
	}

	public void setNumberOfMethods(int numberOfMethods) {
	
		this.numberOfMethods = numberOfMethods;
	
	}






	public int getNumberOfLines() {
	
		return numberOfLines;
	
	}

	public void setNumberOfLines(int numberOfLines) {

		this.numberOfLines = numberOfLines;
	
	}


	//Main Class
	public static void main( String[] args ){
		
		System.out.println(new Metrics().getMetrics(PATH));
		
    }
}
