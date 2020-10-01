package virtualkey;

//import of necessary packages
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//class to perform necessary actions on files in root folder
public class WorkWithUserFiles {
	
	//our file storage folder
	private static File root_dir = new File("../root");
	
	//entered by the user file name
	private static String user_file_name;

	//function to print a failure message
	public static void printError(String message) {
		System.err.println();		
		System.err.println("----------Attention!------------------");
		System.err.println(message);
		System.err.println("----------Attention!------------------");			
	}
	
	//function to print a success message
	public static void printSuccess(String message) {
		System.err.println();		
		System.err.println("----------Success!------------------");
		System.err.println(message);
		System.err.println("You have returned to the menu to work with files!");
		System.err.println("----------Success!------------------");			
	}	
	
	//Safe way to get absolute path of the file. In case of an exception the file name itself is being returned.
	public static String getAbsolutePath (File file) {
		
		String AbsolutePath = "";
		
		try { 
			AbsolutePath = file.toPath().toAbsolutePath().normalize().toString();
		} catch (InvalidPathException | IOError | SecurityException ex) {
			AbsolutePath = file.getName();
		}
			
		return AbsolutePath;
	}
	
	//Check for the existence of the file
	public static boolean CheckFileExistence(File file) {
		
		boolean file_exists = false;
		
		try {
		   file_exists = file.exists();	
		} catch (SecurityException ex){
			printError("Error: an unexpected error occured while trying to check the existence of the file: "+file.getName()+" Error message: "+ex.getMessage());
		}
		
		return file_exists;
	}
	
	//function to check if the file storage folder ("root") exists
	public static boolean RootDirExists() {
		return CheckFileExistence(root_dir);
	}
	
	//Function to add, delete or search for a file
	public static void AddDeleteSearchAFile(UserInterface.UserChoice user_choice) {
		
		boolean b = false; //boolean flag for the loop end
		
		//main loop of the action
		do {
			
			//always check if root folder exists before each action
			if(!RootDirExists()) {
				printError("'root' directory can't be found. Please create the directory if it doesn't exist.");
				return;
			}
			
			//get the text description of the action for text messages for different actions - add, delete or search
			String action = user_choice.getAction();
			
			//display the message to the user
			UserInterface.console.writer().println();
			UserInterface.console.writer().println("-----------------------------------------------");
			UserInterface.console.writer().println("Please type a file name to be "+user_choice.getAction()+" or type 'exit' to return to the menu to work with files:");
			UserInterface.console.writer().println("-----------------------------------------------");		
			UserInterface.console.writer().println();
			
			//get the file name entered
			user_file_name = UserInterface.console.readLine();
			
			//control the answer from user
			if(user_file_name.length() == 0) {
				printError("No characters typed. Please type at least one character!");
				continue;
			} else if (user_file_name.equalsIgnoreCase("exit")) //if "exit" was typed, returning to the menu 
				break;
			
			//creating a file object
			File new_file = new File(root_dir, "/"+user_file_name);
			
			boolean file_exists = false;
			
			//check for the existence of the file
			file_exists = CheckFileExistence(new_file);
			
			//perform necessary checks for ADD & DELETE actions
			//display an error message if file exists and we have add action
			if(file_exists && user_choice.equals(UserInterface.UserChoice.ADD)) {
				printError("File \""+user_file_name+"\" already exists. Please chose a different name for the file to be created.");
				continue;
			//display an error message if file doesn't exist and we have delete action	
			} else if (!file_exists && user_choice.equals(UserInterface.UserChoice.DELETE)) {
				printError("File \""+user_file_name+"\" does not exist. Please chose a different name for the file to be deleted.");
				continue;				
			}

			//In case search menu option is chosen we search for a file 
			if(user_choice.equals(UserInterface.UserChoice.SEARCH)) {
				
				if(file_exists) { //success, file is found
					printSuccess("The file \""+user_file_name+"\" with the possible absolute path \""+getAbsolutePath(new_file)+"\" has been found!");					
				} else { //file is not found
					printError("The file \""+user_file_name+"\" with the absolute path \""+getAbsolutePath(new_file)+"\" has not been found!");										
				}

				break;
			}
			
			try {
				if(user_choice.equals(UserInterface.UserChoice.ADD))
			       b = new_file.createNewFile(); //adding a file to the directory
				else if(user_choice.equals(UserInterface.UserChoice.DELETE))
				   b = new_file.delete(); //deleting a file from the directory
			} catch (IOException | SecurityException ex) {
				printError("Error: an unexpected error occured while working with a file: "+user_file_name+" Error message: "+ex.getMessage());
				break;
			}
			
			if(b) //in case of a success (it means end of the loop) print a corresponding mesaage
				printSuccess("The file \""+user_file_name+"\" has been successfully "+user_choice.getAction());
			
		} while (!b);
	}	
	
	//function to display list of the files in the "root" folder in the ascending order
	public static void DisplayFiles() {

		//always check if root folder exists before each action
		if(!RootDirExists()) {
			printError("'root' directory can't be found. Please create the directory if it doesn't exist.");
			return;
		}

		//display the text message
		UserInterface.console.writer().println();
		UserInterface.console.writer().println("-----------------------------------------------");
		UserInterface.console.writer().println("List of the files found in the directory:");
		UserInterface.console.writer().println("-----------------------------------------------");		
		UserInterface.console.writer().println();
		
		int i = 0;
		
		File[] files;
		
		try {
		   files = root_dir.listFiles(); //getting the list of files in the directory
		} catch (SecurityException ex) {
			printError("Error: an unexpected error occured while trying to get the list of files in the directory."+ex.getMessage());
			return;
		}
		
		List<File> filesList = new ArrayList<File>();
		
		if (files.length > 0) { //if there are files in the directory
		   Collections.addAll(filesList, files); //copy the list of files to a collection
		   Collections.sort(filesList); //sort the list in the ascending order
		}   
		
		for(File file:filesList) { //printing the results - found files
			System.out.println("File \""+file.getName()+"\" has been found. The possible absolute path is \""+getAbsolutePath(file)+"\"");
			i++;
		}
		
		//end of the message to user
		UserInterface.console.writer().println();
		UserInterface.console.writer().println("-----------------------------------------------");
		
		//The number of files found is being printed as well
		if (i>0)
			UserInterface.console.writer().println("Totally files has been found in the directory: "+i);
		else
			UserInterface.console.writer().println("No files has been found in the directory.");
		
		UserInterface.console.writer().println("You are being returned to the main menu.");
		UserInterface.console.writer().println("-----------------------------------------------");		
		UserInterface.console.writer().println();		
		
	}
	
}
