package virtualkey;

import java.io.Console;

//the main class to display the interface for the user
public class UserInterface {
	
	//bbolean var to control the the correctness of the option chosen
	private static boolean IsUserAnswerCorrect = false;
	
	//user's chosen option
	static char user_answer;
	
	//the console being diaplayed - either 1 or 2
	static short console_to_display = 1;

	//enumeration was added just to make the code nicer & more understandable, in fact is not required
	public enum UserChoice {
		//three possible actions with corresponding text for the text messages
		ADD("created"), DELETE("deleted"), SEARCH("searched for");
		
		public String action;

		//private constructor to align the text message ("action") with the option selected
		private UserChoice(String string) {
			this.action = string;
		}
		
		//return the text description of the action selected for the text messages
		public String getAction() {
			return this.action;
		}
		   
	}

	// get the system console
	static Console console = System.console();	
	
	//just a setter
	public static void setIsUserAnswerCorrect(boolean isUserAnswerCorrect) {
		IsUserAnswerCorrect = isUserAnswerCorrect;
	}

	//Displays either first or second menu
	public static void DisplayMenu() {
		
		//just for the nicer design
		console.writer().println();
		console.writer().println("-----------------------------------------------");
		
		if(console_to_display == 1) {
			
		   //Displaying the main (first) menu
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
	
	//reads and verifies user's answer
	public static boolean GetTheAnswer() {
		
		//Getting the answer from the user (the first symbol)
		String users_choice = console.readLine();
		
		//Just pressing the "enter" us nit allowed
		if(users_choice.length() == 0) {
			WrongOption("Error: No characters typed. Please type at least one character!");
			return false;
		}
		//all options are of length "1", hence answers with greater length are not being accepted
		else if(users_choice.length() > 1) {
			WrongOption("Error: Wrong number of characters typed. Please type only one character!");
			return false;
		}	
		
		user_answer = users_choice.charAt(0);
		return true;
		
	}
	
	//function to terminate the application in case a corresponding choice has been made
	public static void ExitFromTheApplication() {
		System.out.println("Exiting from the application.. Many thanks and bye!");
		System.exit(0);
	}
	
	//error message about wrong option chosen
	public static void WrongOption(String ErrorMessage) {
		
		System.err.println();		
		System.err.println("----------Attention!------------------");
		System.err.println(ErrorMessage);
		System.err.println("The menu will be displayed again! Please chose the correct option!!");
		System.err.println("----------Attention!------------------");		
	}

	//main method
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
		
		//main do-while loop
		do {
			
			//display the menu
			DisplayMenu();
			//get user's answer
			IsUserAnswerCorrect = GetTheAnswer();
			//in case no correct option is selected, continue
			if(!IsUserAnswerCorrect) continue;
			
			//switch statements with corresponding actions for both menus
			if (console_to_display == 1) { //first (main) menu (console)
			   switch(user_answer) {
			      case '1': //display files in ascending order
				         WorkWithUserFiles.DisplayFiles();
			             break;
			      case '2': //enter console 2
				         console_to_display = 2;
			             break;
			      case '3': //terminate the application
				         ExitFromTheApplication();
			             break;
			      //wrong option selected       
			      default: WrongOption("Error: Not existing option selected. Please select either [1] or [2] or [3]!");
			   }
			} else if (console_to_display == 2) { //second menu (console)
				   switch(user_answer) {
				      case 'a': //add a file
					         WorkWithUserFiles.AddDeleteSearchAFile(UserChoice.ADD);
				             break;
				      case 'd': //delete a file
				    	     WorkWithUserFiles.AddDeleteSearchAFile(UserChoice.DELETE);
				             break;
				      case 's': //search for a file
				    	     WorkWithUserFiles.AddDeleteSearchAFile(UserChoice.SEARCH);
				             break;
				      case 'e': //return to console 1
				    	     console_to_display = 1;
				    	     break;
				      //wrong option selected     
				      default: WrongOption("Error: Not existing option selected. Please select either [a] or [b] or [s] or [e]!");
				   }				
			}
		} while (true);
		

	}

}
