package virtualkey;

import java.io.Console;

public class UserInterface {
	
	private static boolean IsUserAnswerCorrect = false;
	
	static char user_answer;
	
	static short console_to_display = 1;
	
	// get the console
	static Console console = System.console();	
	
	public static void setIsUserAnswerCorrect(boolean isUserAnswerCorrect) {
		IsUserAnswerCorrect = isUserAnswerCorrect;
	}

	public static void DisplayMenu() {
		
		//just for the nicer design
		console.writer().println();
		console.writer().println("-----------------------------------------------");
		
		if(console_to_display == 1) {
			
		   //Displaying the main menu
		   console.writer().println("Main Menu - please select one of the options:");
		   console.writer().println("[1] - Display the list of files in ascending order.");
		   console.writer().println("[2] - Work with files directory.");
		   console.writer().println("[3] - Terminate the application.");
		   
		   //Displaying the second menu
		} else if (console_to_display == 2) {
			
			   console.writer().println("Working with files- please select one of the options:");
			   console.writer().println("[a] - Add a file.");
			   console.writer().println("[d] - Delete a file.");
			   console.writer().println("[s] - Search for a file");
			   console.writer().println("[e] - Return to the main menu.");			   
		}
		
		//just for the nicer design
		console.writer().println("-----------------------------------------------");	
		console.writer().println();
		
		//flush
		console.flush();
	
	}
	
	public static boolean GetTheAnswer() {
		
		//Getting the answer from the user (the first symbol)
		String users_choice = console.readLine();
		
		if(users_choice.length() == 0) {
			WrongOption("Error: No characters typed. Please type at least one character!");
			return false;
		}			
		else if(users_choice.length() > 1) {
			WrongOption("Error: Wrong number of characters typed. Please type only one character!");
			return false;
		}	
		
		user_answer = users_choice.charAt(0);
		return true;
		
	}
	
	public static void ExitFromTheApplication() {
		System.out.println("Exiting from the application.. Many thanks and bye!");
		System.exit(0);
	}
	
	public static void WrongOption(String ErrorMessage) {
		
		System.err.println();		
		System.err.println("----------Attention!------------------");
		System.err.println(ErrorMessage);
		System.err.println("The menu will be displayed again! Please chose the correct option!!");
		System.err.println("----------Attention!------------------");		
	}

	public static void main(String[] args) {
		
		//Printing an error message in case console is not available for any reason
		if(console == null) {
			System.err.println("User Console is not available. Application Terminated. Try using the application from the command line using the command \"java UserInterface\"");
			System.exit(1);
		}

		//Displaying the application information & menu
		console.writer().println();
		console.writer().println("////////////////////////////////////////////////");
		console.writer().println("'Virtual Key for Your Repositories' Application");
		console.writer().println("Java Developer: Dmitry Koryanov");
		console.writer().println("////////////////////////////////////////////////");
		console.writer().println();
				
		do {
			
			DisplayMenu();
			IsUserAnswerCorrect = GetTheAnswer();
			if(!IsUserAnswerCorrect) continue;
			
			if (console_to_display == 1) {
			   switch(user_answer) {
			      case '1': 
				         WorkWithUserFiles.DisplayFiles();
			             break;
			      case '2': 
				         console_to_display = 2;
			             break;
			      case '3': 
				         ExitFromTheApplication();
			             break;
			      default: WrongOption("Error: Not existing option selected. Please select either [1] or [2] or [3]!");
			   }
			} else if (console_to_display == 2) {
				   switch(user_answer) {
				      case 'a': 
					         WorkWithUserFiles.AddAFile();
				             break;
				      case 'd': 
				    	     WorkWithUserFiles.DeleteAFile();
				             break;
				      case 's': 
				    	     WorkWithUserFiles.SearchForAFile();
				             break;
				      case 'e':
				    	     console_to_display = 1;
				    	     break;
				      default: WrongOption("Error: Not existing option selected. Please select either [a] or [b] or [s] or [e]!");
				   }				
			}
		} while (true);
		

	}

}
