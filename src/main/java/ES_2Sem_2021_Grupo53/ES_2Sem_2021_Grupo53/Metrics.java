package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


//Metric Extraction File
public class Metrics {
    
	private int numberOfPackages = 0; //Increment every time we enter a new folder
	private int numberOfClasses = 0; //Increment every time we enter a new file
	private int numberOfMethods = 0;//Increment every time we enter a new method
	private int numberOfLines = 0;//Increment every time we enter a new line
	private ArrayList<File> files;
	
	/**
	 * A Method that extracts various methods
	 * 
	 * Extracts The number of packages and classes and methods and total number of lines in a folder indicated by the user,
	 * as well as for each class the number of lines, number of methods and the cyclomatic complexity
	 * (how many loops are in the method with the most loops in that class) of the class furthermore,
	 * it extracts for each method the number of lines of code and the cyclomatic complexity(number of loops).
	 * 
	 * @param Path(Path of the folder to analyse)
	 * 
	 * @return for now just an array with two integers like so: [numberOfLinesClass, numberOfMethodsClass] 
	 * 
	 * @throws Exception in case it can't open the file 
	 */
	
	public int[] getMetrics(String Path) {
		
		try {
			
			File fileOrigins = new File(Path);
			decomposePackages(fileOrigins);
			ArrayList<File> files = getFiles();
			for(int i = 0; i < files.size(); i++) {
				
				BufferedReader file = new BufferedReader(new FileReader(files.get(i)));
				
				String line;
				String className;

				int numberOfLinesClass = 0; // Increment every new line in the class
				int numberOfMethodsClass = 0; //Increment every time we enter a new method in the class
				int numberOfLinesMethod = 0;//Increment every new line till end of method
				int numberOfLoops = 0;//Increment every time there is a for or while loop in the method

				ArrayList<Integer> methodsCyclomatic = new ArrayList<Integer>();

				int[] answers = new int[2];
				answers[0] = 0;
				answers[1] = 0;

				while((line = file.readLine()) != null) {

					if(!line.trim().isEmpty() && !line.contains("//") && !line.contains("package") && !line.contains("import")) { // Checks if it is a valid line i.e. is not an import or package statement and is not a comment or empty line

						if(numberOfLinesClass == 0) {

							className = getClassName(line); 
						}

						numberOfLinesClass++;

						if(isMethod(line)) {
							
							//If new Method we have to add the cyclomatic and num of lines of method to the xlsx file and reset the counters to 0
							numberOfLinesMethod = 0;
							numberOfMethodsClass++;
							numberOfLoops = 0;
							setNumberOfMethods(getNumberOfMethods() + 1);
							methodsCyclomatic.add(numberOfLoops);
							//Print method info to xlsx file
							
						}
						
						numberOfLinesMethod++;
						// if line has a for or a while 
						numberOfLoops++;

					}
				}
				
				int classComplexity = 0; //Set to max of array of methodsCyclomatic 
				setNumberOfClasses(getNumberOfClasses() + 1);
				setNumberOfLines(getNumberOfLines() + numberOfLinesClass);
				//Print class info to xlsx file

				file.close();

				answers[0] = numberOfLinesClass;
				answers[1] = numberOfMethodsClass;

				return answers;

			}
			
		}catch(Exception e){

			e.printStackTrace();

		}
	//1-Open files from path
		//If path is empty throw exception(Return false) failure
	//2-Loop to parse each packages
	//3-Loop to parse each file in the packages
		int [] def = new int[2];
		return def;
	}
	
	/**
	 * Takes a Line of Code(String) and returns the class name for that line
	 * 
	 * Splits the string by " " and checks if the last item is "{" if it is returns the second to last item 
	 * and if it is not returns the last item without the last char
	 *  
	 * WARNING does not check whether the line of code is a class so be careful passing lines of code to it
	 * 
	 * @param line (String to extract the class name from)
	 * @return class name (String)
	 */
	
	public String getClassName(String line){
		
		String[] helper = line.split(" ");
		
		if(helper[helper.length - 1].equals("{")) {
			
			return helper[helper.length -2];
			
		}else{
			String s = helper[helper.length -1];
			return s.substring(0, s.length() - 1 );
			
		}
		
	}
	
	/**
	 * Method that determines whether a given line of code is the start of a method
	 * 
	 * First it checks whether the line of code has parenthesis in it and if it does splits the string by "(" if it does not returns false
	 * and finally takes the first item of the split string and splits it again this time by " " then it checks if the first item in this 
	 * array is the word "private", "public" or static or if that word has a data type i. e. String, int[], ArrayList<char> etc if it does
	 * returns true and if it does not returns false.
	 * 
	 * @param line (String line of code)
	 * @return boolean true if it is a Method and false if it is not
	 */
	
	public boolean isMethod(String line) {
		
		if(line.indexOf("(") >= 0){
			
			String[] help = line.split("\\(");
			String[] helpNext = help[0].split(" ");
			helpNext[0] = helpNext[0].trim();
			
			if(!helpNext[0].contains("print") && (helpNext[0].equals("private") || helpNext[0].equals("public") || helpNext[0].equals("static") || helpNext[0].equals("void") || helpNext[0].contains("int") || helpNext[0].contains("String") || helpNext[0].contains("boolean") || helpNext[0].contains("char"))) {
				
				return true;
				
			}else {
				
				return false;
				
			}
		
		}else {
			
			return false;
			
		}
		
	}
	
public void decomposePackages(File path) {
		
	setNumberOfPackages(1);//1 or 0 depends on whether we count the original package 
	
	for (File fileEntry : path.listFiles()) {
	   
		if (fileEntry.isDirectory()) {
	   	
	    	decomposePackages(fileEntry);
	    	
	    	setNumberOfPackages(getNumberOfPackages() + 1);
	    	
	    } else {
	
	    	getFiles().add(fileEntry);
	    	
	    }
	 
	}
	
}
		
	
		
	public int getNumberOfPackages() {
	
		return numberOfPackages;
	
	}

	public void setNumberOfPackages(int numberOfPackets) {
	
		this.numberOfPackages = numberOfPackets;
	
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

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

}
