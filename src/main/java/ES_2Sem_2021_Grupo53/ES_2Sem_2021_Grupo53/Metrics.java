package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Metric Extraction File
public class Metrics {

	private int numberOfPackages = 0; //Increment every time we enter a new folder
	private int numberOfClasses = 0; //Increment every time we enter a new file
	private int numberOfMethods = 0;//Increment every time we enter a new method
	private int numberOfLines = 0;//Increment every time we enter a new line
	private ArrayList<File> files = new ArrayList<File>();

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

	public boolean getMetrics(String Path) {

		try {
			
			//Create XLSX File
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
	         
		     XSSFSheet sheet = workbook.createSheet("Dummy");		     
		     //Create Firsts Row
		     Row row = sheet.createRow(0);
		     
		     //Create titles to add to first row
		     String [] titles = new String[11];
		     titles[0] = "MethodID";
		     titles[1] = "Package";
		     titles[2] = "Class";
		     titles[3] = "Method";
		     titles[4] = "NOM_Class";
		     titles[5] = "LOC_class";
		     titles[6] = "WMC_Class";
		     titles[7] = "is_God_Class";
		     titles[8] = "LOC_Method";
		     titles[9] = "CYCLO_Method";
		     titles[10] = "is_Long_Method";
		     
		     //Setting normal font
		     XSSFFont defaultFont = workbook.createFont();
		     defaultFont.setFontHeightInPoints((short)10);
		     defaultFont.setFontName("Arial");
		     defaultFont.setColor(IndexedColors.BLACK.getIndex());
		     defaultFont.setBold(false);
		     defaultFont.setItalic(false);
		     
		     //Titles font
		     XSSFCellStyle style = workbook.createCellStyle();
		     
		     XSSFFont font = workbook.createFont();
		     
		     font.setFontHeightInPoints((short) 15);
		     font.setBold(true);
		     
		     style.setFont(font);       
		     
 		     //Add Titles to row
		     for(int i = 0; i < titles.length; i++) {
		    	 
		    	Cell cell = row.createCell(i);
		     
		     	cell.setCellValue(titles[i]);
		     
		     	cell.setCellStyle(style);

		     }
		     
		    //Create File with the original PATH and call decomposePackages to get all the classes and the number of packages
			File fileOrigins = new File(Path);
			decomposePackages(fileOrigins);
			ArrayList<File> files = getFiles();

			//Outer Class Loop
			for(int i = 0; i < files.size(); i++) {

				//Open Class File 
				BufferedReader file = new BufferedReader(new FileReader(files.get(i)));

				//Declaring initial values for variables
				String line;
				String className;
				String methodName = "";

				int numberOfLinesClass = 0; // Increment every new line in the class
				int numberOfMethodsClass = 0; //Increment every time we enter a new method in the class
				int numberOfLinesMethod = 0; //Increment every new line till end of method
				int numberOfLoops = 0; //Increment every time there is a for or while loop in the method
				

				ArrayList<Integer> methodsCyclomatic = new ArrayList<Integer>();

				//Inner Class Loop
				while((line = file.readLine()) != null) {

					if(!line.trim().isEmpty() && !line.contains("//") && !line.contains("package") && !line.contains("import")) { // Checks if it is a valid line i.e. is not an import or package statement and is not a comment or empty line

						//Gets the class name
						if(numberOfLinesClass == 0) {

							className = getClassName(line);
							
						}

						//Increments the LOC_Class metric
						numberOfLinesClass++;
						
						//Increments the LOC_Method metric
						if(numberOfLinesClass > 1){
						
							numberOfLinesMethod++;
						
						}
						
						//Increments the CYCLO_Method metric
						if(line.trim().startsWith("for") || line.trim().startsWith("while")){ // if line has a for or a while 
						
							numberOfLoops++;
						
						}
						
						//If it is a new method gets its name resets method metrics, increments NOM_Class metric and total number of methods metrics, also puts the CYCLO_Method metric in an array list for further use
						if(isMethod(line)) {
							
							methodName = getMethodName(line);
							numberOfLinesMethod = 0;
							numberOfMethodsClass++;
							methodsCyclomatic.add(numberOfLoops);
							numberOfLoops = 0;
							setNumberOfMethods(getNumberOfMethods() + 1);
							
							//Print method info to xlsx file
						}			
																		
					}
				}
				
				//Adds the last methods CYCLO_Metod metric to the list
				methodsCyclomatic.add(numberOfLoops);
				
				//Gets the max in the CYCLO_Method list to define WMC_Class metric
				int numberOfLoopsClass = Collections.max(methodsCyclomatic);
				
				//Increment NUmber of total classes by 1 and number of total lines of code by LOC_Class
				setNumberOfClasses(getNumberOfClasses() + 1);
				setNumberOfLines(getNumberOfLines() + numberOfLinesClass);

				file.close();
				
				//auto sizing every column
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
				sheet.autoSizeColumn(9);
				sheet.autoSizeColumn(10);
				
				
				//Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File("Dummy.xlsx"));
	            workbook.write(out);
	            out.close();

			}

		}catch(Exception e){

			e.printStackTrace();

		}
		//1-Open files from path
		//If path is empty throw exception(Return false) failure
		//2-Loop to parse each packages
		//3-Loop to parse each file in the packages

		return true;
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
	 * A method that receives a line of code and returns the method name
	 * 
	 *  First it splits the string by "("  and takes the first item of the split string and splits it again this time by " " then it 
	 * 	returns the last String in the new String[] without its last character to avoid the "(" that is split along with the method name.
	 * 	WARNING it does not check whether line is a method so be careful with the arguments being passed to it .
	 * 
	 * @param line(String line of code)
	 * @return method name
	 */

	public String getMethodName(String line){

		String[] ogHelper = line.split("\\(");
		String[] helper = ogHelper[0].split(" ");

		String answer = helper[helper.length -1];

		return answer.substring(0, answer.length());

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

	/**
	 * Takes the path and counts the number of packages and puts every file in an Array 
	 * 
	 * This method counts every package in the given path and puts every file(class) in an array of 
	 * files for metric extraction
	 * 
	 * @param path (Sting)
	 */

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