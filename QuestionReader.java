import java.io.*;/**QuestionReader can read data from text and store it in String array*/
public class  QuestionReader {/** Name of the file to be read*/
	public String fileName; /**Questions in the file*/ 
	public String[] QUESTIONS = new String[4];/** All first options of all questions in file*/
	public String[] OPTION1 = new String[4];/** All second options of all questions in file*/
	public String[] OPTION2 = new String[4];/** All third options of all questions in file*/
	public String[] OPTION3 = new String[4];/** All fourth options of all questions in file*/
	public String[] OPTION4 = new String[4];
	private File file;
	private int difficulty;/** Initialize QuestionReader*/
	public QuestionReader(int difficulty){
		this.difficulty = difficulty;
		readFiles();
	}/** readFiles() can read text from different question files. If the file is not found, the program will print "Errors occured" and exit*/
	public void readFiles(){
		if(difficulty==1){/* read files based on the parameter passed in*/
			file = new File("Easy level questions.txt");
			fileName=file.getAbsolutePath();
			fileName=fileName.replace("Code","Files");
		}
		else if(difficulty==2){
			file = new File("Medium level questions.txt");
			fileName=file.getAbsolutePath();
			fileName=fileName.replace("Code","Files");
		}
		else{
			file = new File("Difficult level questions.txt");
			fileName=file.getAbsolutePath();
			fileName=fileName.replace("Code","Files");
		}
		try {
			FileReader fileReader = new FileReader(fileName); /* Create a fileReader object*/
			BufferedReader bufferedReader = new BufferedReader(fileReader);/* Use the bufferedReader to read the file*/
			for(int i=0;i<=3;i++){
				QUESTIONS[i] = bufferedReader.readLine();/* Store data read from file in String array*/
				OPTION1[i] = bufferedReader.readLine();
				OPTION2[i] = bufferedReader.readLine();
				OPTION3[i] = bufferedReader.readLine();
				OPTION4[i] = bufferedReader.readLine();
			}
			bufferedReader.close(); /* Close the bufferedReader*/
			fileReader.close();/* C;ose the fileReader*/
		}
		catch (Exception e) {
		System.out.println("Errors occured"); /* Print out "Errors occured" when system cahtch an IOException*/
		System.exit(1); /* Exit the program when exception happen*/
		}
	}
}