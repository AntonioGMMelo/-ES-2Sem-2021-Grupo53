package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

//Metric Extraction File
public class Metrics {
    
	private int numberOfPackets = 0; //Increment every time we enter a new folder
	private int numberOfClasses = 0; //Increment every time we enter a new file
	private int numberOfMethods = 0;//Increment every time we enter a new method
	private int numberOfLines = 0;//Increment every time we enter a new line
	public static final String PATH = "./Dummy"; //Replace with user specified path when GUI is operational
	
	private boolean getMetrics(String Path) {
		
		try {
			
			BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\anton\\eclipse-workspace\\ES\\ES-2Sem-2021-Grupo53\\src\\main\\java\\ES_2Sem_2021_Grupo53\\ES_2Sem_2021_Grupo53\\Dummy\\DummyClass2.java"));
			
			String line;
			int numberOfLinesClass = 0; // Increment every new line
			int numberOfLinesMethod = 0;//Increment every new line till end of method
			int numberOfLoops = 0;//Increment every time there is a for or while loop in the code
			ArrayList<Integer> methodsCyclomatic = new ArrayList<Integer>();
			
			while((line=file.readLine()) != null) {
				
				if(!line.trim().isEmpty() && !line.contains("//") && !line.contains("package") && !line.contains("import")) { // Checks if it is a valid line i.e. is not an import or package statement and is not a comment or empty line
					
					numberOfLinesClass++;
						
					//If new Method we have to add the cyclomatic and num of lines of method to the xlsx file and reset the counters to 0
						numberOfLinesMethod = 0;
						numberOfLoops = 0;
						setNumberOfMethods(getNumberOfMethods() + 1);
						methodsCyclomatic.add(numberOfLoops);
						//Print method info to xlsx file
					numberOfLinesMethod++;
					// if line has a for or a while 
						numberOfLoops++;
					
				}
			}
			System.out.println(numberOfLinesClass);
			int classComplexity = 0; //Set to max of array of methodsCyclomatic 
			setNumberOfClasses(getNumberOfClasses() + 1);
			setNumberOfLines(getNumberOfLines() + numberOfLinesClass);
			//Print class info to xlsx file
			
			
			file.close();

		}catch(Exception e){
		
			e.printStackTrace();
		
		}
	//1-Open files from path
		//If path is empty throw exception(Return false) failure
	//2-Loop to parse each packages
		setNumberOfPackets(getNumberOfPackets() + 1);
	//3-Loop to parse each file in the packages
		return true;
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
