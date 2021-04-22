package ES_2Sem_2021_Grupo53.ES_2Sem_2021_Grupo53;

import java.util.ArrayList;
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
	 * (how many branches ( 1 + ifs, whiles, fors, and cases ) are in all the methods in that class) of the class furthermore,
	 * it extracts for each method the number of lines of code and the cyclomatic complexity(number of branches).
	 * Finally it prints each method entry with the metrics following the template described bellow to a xlsx file:
	 * 
	 * MethodID(Auto Increment) | Package Name | Class Name ! Method Name | LOC_Class | NOM_CLass | WMC_CLass | is_God_Class | LOC_Method | CYCLO_Method ! is_Long_Method
	 * 
	 * @param Path(Path of the folder to analyse)
	 * 
	 * @return Boolean true for successful operation
	 * 
	 * @throws IllegalArgumentException in case it can't open one of the files 
	 */

	public boolean getMetrics(String Path, ArrayList<String> orderOfMethods, ArrayList<String> logicMethods, ArrayList<Integer> thresholdsMethods, ArrayList<String> orderOfClass, ArrayList<String> logicClass, ArrayList<Integer> thresholdsClass) {
 
		try {
			
			//Get Name For The XLSX File
			String[] pathHelp = Path.split("\\\\");
			String xlsxFileName = pathHelp[pathHelp.length -1];
			
			//Create XLSX File
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
	         
		     XSSFSheet sheet = workbook.createSheet(xlsxFileName);		     
		     //Create Firsts Row
		     Row row = sheet.createRow(0);
		     
		     //Create Titles To Add To First Row
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
		     
		     //Setting Normal Font
		     XSSFFont defaultFont = workbook.createFont();
		     defaultFont.setFontHeightInPoints((short)10);
		     defaultFont.setFontName("Arial");
		     defaultFont.setColor(IndexedColors.BLACK.getIndex());
		     defaultFont.setBold(false);
		     defaultFont.setItalic(false);
		     
		     //Titles Font
		     XSSFCellStyle style = workbook.createCellStyle();
		     
		     XSSFFont font = workbook.createFont();
		     
		     font.setFontHeightInPoints((short) 15);
		     font.setBold(true);
		     
		     style.setFont(font);       
		     
 		     //Add Titles To Row
		     for(int i = 0; i < titles.length; i++) {
		    	 
		    	Cell cell = row.createCell(i);
		     
		     	cell.setCellValue(titles[i]);
		     
		     	cell.setCellStyle(style);

		     }
		     
		    //Create File With The Original Path And Call decomposePackages To Get All The Classes And The Number Of Packages
			File fileOrigins = new File(Path);
			decomposePackages(fileOrigins);
			ArrayList<File> files = getFiles();
			
			int methodID = 1;

			//Outer Class Loop
			for(int i = 0; i < files.size(); i++) {
				
				//get file package
				String[] pathHelper = Path.split("\\\\");
				String packageName = pathHelper[pathHelper.length -1];

				//Open Class File 
				BufferedReader file = new BufferedReader(new FileReader(files.get(i)));

				//Declaring Initial Values For Variables
				String line;
				String className = "";
				String methodName = "";

				int numberOfLinesClass = 0; // Increment every new line in the class
				int numberOfMethodsClass = 0; //Increment every time we enter a new method in the class
				int numberOfLinesMethod = 0; //Increment every new line till end of method
				int numberOfBranches = 1; //Increment every time there is a for or while loop in the method
				int numberOfBranchesClass = 0;
				
				ArrayList<Integer> metrics = new ArrayList<Integer>();;

				//Inner Class Loop
				while((line = file.readLine()) != null) {

					if(!line.trim().isEmpty() && !line.contains("//") && !line.contains("package") && !line.contains("import")) { // Checks If It Is A Valid Line i.e. Is Not An Import Or Package Statement And Is Not A Comment Or Empty Line

						//Gets The Class Name
						if(numberOfLinesClass == 0) {

							className = getClassName(line);
							
						}

						//Increments The LOC_Class Metric
						numberOfLinesClass++;
						
						//Increments The LOC_Method Metric
						if(numberOfLinesClass > 1){
						
							numberOfLinesMethod++;
						
						}
						
						//Increments The CYCLO_Method Metric
						if(line.trim().startsWith("for") || line.trim().startsWith("while") || line.trim().startsWith("if") || line.trim().startsWith("case") ){ // if line has a for or a while 
						
							numberOfBranches++;
						
						}
						
						//If It Is A New Method Gets Its Name Resets Method Metrics, Increments NOM_Class Metric And Total Number Of Methods Metric, Also Puts The CYCLO_Method Metric In An Array List For Further Use And Increments WMC_Method By CYCLO_Method
						if(isMethod(line)) {
							
							if(!methodName.equals("")) {
								
								//Write To File
								Row tempRow = sheet.createRow(methodID);
							
								//Write Method ID
								Cell cell = tempRow.createCell(0);
					     		cell.setCellValue(methodID);
					     	
					     		//Write Package Name
					     		cell = tempRow.createCell(1);
					     		cell.setCellValue(packageName);
					     	
					     		//Write Class Name
					     		cell = tempRow.createCell(2);
					     		cell.setCellValue(className);
					     	
					     		//Write Method Name
					     		cell = tempRow.createCell(3);
					     		cell.setCellValue(methodName);
					     	
					     		//LOC_Method
					     		cell = tempRow.createCell(8);
					     		cell.setCellValue(numberOfLinesMethod);
					     		
					     		//CYCLO_Method
					     		cell = tempRow.createCell(9);
					     		cell.setCellValue(numberOfBranches);
					     	
					     		//Check is_Long_Method
					     		metrics = new ArrayList<Integer>();
					     		
					     		for(String s : orderOfMethods) {
					     			
					     			switch(s){
					     			
					     			case "LOC_Method":
					     				metrics.add(numberOfLinesMethod);
					     				break;
					     			
					     			case "CYCLO_Method":
					     				metrics.add(numberOfBranches);
					     				break;
					     				
					     			}
					     			
					     		}
					     		
					     		//is_Long_Method
					     		cell = tempRow.createCell(10);
					     		cell.setCellValue(isCodeSmell(metrics, logicMethods, thresholdsMethods)); // isCodeSmell
						
					     		methodID++;
							
							}
							
							methodName = getMethodName(line);
							numberOfLinesMethod = 0;
							numberOfMethodsClass++;
							numberOfBranchesClass += numberOfBranches;
							numberOfBranches = 1;
							setNumberOfMethods(getNumberOfMethods() + 1);
							
						}			
																		
					}
				}
				
				//Add Last Method
				
				//Write To File
				Row tempRow = sheet.createRow(methodID);
				
				//Write Method ID
				Cell cell = tempRow.createCell(0);
		     	cell.setCellValue(methodID);
		     	
		     	//Write Package Name
		     	cell = tempRow.createCell(1);
		     	cell.setCellValue(packageName);
		     	
		     	//Write Class Name
		     	cell = tempRow.createCell(2);
		     	cell.setCellValue(className);
		     	
		     	//Write Method Name
		     	cell = tempRow.createCell(3);
		     	cell.setCellValue(methodName);
		     	
		     	//LOC_Method
		     	cell = tempRow.createCell(8);
		     	cell.setCellValue(numberOfLinesMethod);
		     	
		     	//CYCLO_Method
		     	cell = tempRow.createCell(9);
		     	cell.setCellValue(numberOfBranches);
		     	
		     	//Check is_Long_Method
	     		metrics = new ArrayList<Integer>();
	     		
	     		for(String s : orderOfMethods) {
	     			
	     			switch(s){
	     			
	     			case "LOC_Method":
	     				metrics.add(numberOfLinesMethod);
	     				break;
	     			
	     			case "CYCLO_Method":
	     				metrics.add(numberOfBranches);
	     				break;
	     				
	     			}
	     			
	     		}
		     	
		     	//is_Long_Method
		     	cell = tempRow.createCell(10);
		     	cell.setCellValue(isCodeSmell(metrics, logicMethods, thresholdsMethods)); // isCodeSmell
		    						
				methodID++;
				
				//Increment Number Of Total Classes By 1 And Number Of Total Lines Of Code By LOC_Class
				setNumberOfClasses(getNumberOfClasses() + 1);
				setNumberOfLines(getNumberOfLines() + numberOfLinesClass);

				file.close();
				
				metrics = new ArrayList<Integer>();
				
				for(String s : orderOfClass) {
	     			
	     			switch(s){
	     			
	     			case "LOC_Class":
	     				metrics.add(numberOfLinesClass);
	     				break;
	     			
	     			case "NOM_Class":
	     				metrics.add(numberOfMethodsClass);
	     				break;
	     				
	     			case "WMC_Class":
	     				metrics.add(numberOfBranchesClass);
	     				break;	
	     				
	     			}
	     			
	     		}
				
				
				int index = 0;
				
				while(sheet.getRow(index) != null ) {
					
					if(sheet.getRow(index).getCell(2).getStringCellValue().equals(className)) {
						
						sheet.getRow(index).createCell(4).setCellValue(numberOfLinesClass);
						
						sheet.getRow(index).createCell(5).setCellValue(numberOfMethodsClass);
						
						sheet.getRow(index).createCell(6).setCellValue(numberOfBranchesClass);
						
						sheet.getRow(index).createCell(7).setCellValue(isCodeSmell(metrics, logicClass, thresholdsClass)); // isCodeSmell
						
					}
					
					index++;
					
				}
				
				//Auto Sizing Every Column
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
				
				
				//Write The Workbook In XLSX File
	            FileOutputStream out = new FileOutputStream(new File(xlsxFileName + "_metrics.xlsx"));
	            workbook.write(out);
	            out.close();

			}

		}catch(Exception e){

			e.printStackTrace();
			throw new IllegalArgumentException();
			
		}

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
	
	/*
	 * Method that determines whether it is a code smell or not
	 * 
	 * First checks all the values referred to the metrics extracted and compares them with the thresholds from the user and outputs it to the list of booleans.
	 * Then, using the boolean list formed previously and the list of logic operations from the user, makes all the logic operations to each boolean in the list and outputs it as a single boolean.
	 * 
	 * @param list of all metrics extracted (value), list of all the logic operations inputed by the user, list of all the limit values (thresholds)
	 * @return boolean true if it is a code smell and false if it is not
	 */
	
	public boolean isCodeSmell(ArrayList<Integer> allMetrics, ArrayList<String> logicOp, ArrayList<Integer> thresholds){ 
		ArrayList<Boolean> metricsResult = new ArrayList<Boolean>();		
		
		boolean result = true;
		boolean helper = true;
		String logicOperation = "";
		
		for(int i = 0; i < thresholds.size(); i++){
			if(allMetrics.get(i) <= thresholds.get(i))
				metricsResult.add(i, false);
			else
				metricsResult.add(i, true);
		}
		
		for(int j = 0; j < metricsResult.size(); j++){
			if(j == 0){
				result = metricsResult.get(0);
				logicOperation = logicOp.get(0);
			}
			if(j == metricsResult.size() - 1){
				helper = metricsResult.get(j);
				if(logicOperation.equals("AND")){
					result = result && helper;
				}
				else{
					result = result || helper;
				}
			}
			else{
				helper = metricsResult.get(j);
				switch(logicOperation){
					case "AND":
						result = result && helper;
						break;
					case "OR":
						result = result || helper;
						break;					
				}
				logicOperation = logicOp.get(j);
			}		
		}
		
		return result;
	}

	/**
	 * Takes the path and counts the number of packages and puts every file in an Array 
	 * 
	 * This method counts every package in the given path and puts every file(.java) in an array of 
	 * files for metric extraction.
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

				if(fileEntry.getName().endsWith(".java")) {
					getFiles().add(fileEntry);
				}
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