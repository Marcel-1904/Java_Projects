//This is the capstone that I submitted in Task 7
//Each class have their own files. 
//I have followed the points givin in the compulsory task of Task 20.
//The try and catch blocks have been added inside this file.
//All three files have been refactored and a linter was used with all
//three files. 
//All bugs have been removed.
//I have redone all indentacion and formatting errors.

package CapstoneProject1;

//To give the application some extra detail, here is a console
//that is going to run the run the entire program showing a gui
//with the help of this import it is possible to show a gui
import javax.swing.JOptionPane;

public class projectManagmentSystem {
	public static double getValidDouble(String valueToRequest, String errorToDisplay) {
		// A boolean value of false
		boolean validInput = false;

		// Set number to -1 to be detected as no input if the user selects to "cancel"
		double numberToReturn = -1;

		// While loop continues to loop until a valid double is entered
		while (!validInput) {
			String userSelection;

			// Requests reiterates request in console and popup
			System.out.println("\n" + valueToRequest);
			userSelection = JOptionPane.showInputDialog(valueToRequest);

			// If no value or an alphabetical value is entered the error message is
			// displayed
			// If a correct value is entered, it's cast to a double and the loop is ended
			if (userSelection == null) {
				// If statement if the user wants to cancel their entry
				if (confirmUserChoice("Are you sure you want to cancel entry?")) {
					validInput = true;
				}

			}

			else if (userSelection.isEmpty()) {
				System.out.println("\n" + errorToDisplay);
			}

			else {
				try {
					System.out.println(userSelection);
					numberToReturn = Double.parseDouble(userSelection);
					if (numberToReturn > 0) {
						validInput = true;
					}

					else {
						System.out.println("\n" + errorToDisplay);
					}

				}

				catch (NumberFormatException num) {
					System.out.println("\n" + errorToDisplay);
				}
			}
		}
		return numberToReturn;
	}

	// This method uses the same algorithm as the getValidDouble method, but handles
	// Int values instead
	public static int getValidInt(String valueToRequest, String errorToDisplay) {
		boolean validInput = false;
		int numberToReturn = -1;
		// While loop continues to loop until a valid double is entered
		while (!validInput) {
			String userSelection;

			// Requests reiterates request in console and popup
			System.out.println("\n" + valueToRequest);
			userSelection = JOptionPane.showInputDialog(valueToRequest);

			// If no value or an alphabetical value is entered the error message is
			// displayed
			// If a correct value is entered, it's cast to a double and the loop is ended
			if (userSelection == null) {

				if (confirmUserChoice("Are you sure you want to cancel entry?")) {
					validInput = true;
				}

			}

			else if (userSelection.isEmpty()) {
				System.out.println(errorToDisplay);
			}

			else {
				try {
					System.out.println(userSelection);
					numberToReturn = Integer.parseInt(userSelection);
					if (numberToReturn > 0) {
						validInput = true;
					}

					else {
						System.out.println("\n" + errorToDisplay);
					}
				}

				catch (NumberFormatException num) {
					System.out.println("\n" + errorToDisplay);
				}
			}
		}
		return numberToReturn;
	}

	// This method requests a string from the user.
	// If the user passed a blank value they are displayed an error and input is
	// requested again
	public static String getUserString(String valueToRequest, String errorToDisplay) {
		boolean validInput = false;
		// Set's string to empty to be detected as no entry if passed
		String stringToReturn = "";
		// While loop continues to loop until a valid string is entered
		while (!validInput) {
			String userSelection;
			// Requests reiterates request in console and popup
			System.out.println("\n" + valueToRequest);
			userSelection = JOptionPane.showInputDialog(valueToRequest);

			// If no value or an alphabetical value is entered the error message is
			// displayed
			// If a correct value is entered, it's cast to a double and the loop is ended
			if (userSelection == null) {

				if (confirmUserChoice("Are you sure you want to cancel entry?")) {
					validInput = true;
				}

			}

			else if (userSelection.isEmpty()) {
				System.out.println("\n" + errorToDisplay);
			}

			else {
				System.out.println(userSelection);
				stringToReturn = userSelection;
				validInput = true;
			}
		}
		return stringToReturn;
	}

	// The method asks the user if they would like to
	public static boolean confirmUserChoice(String messageToConfirm) {
		boolean boolToReturn = false;

		int userOption = JOptionPane.showConfirmDialog(
				null,
				messageToConfirm,
				"Poised Project Management",
				JOptionPane.YES_NO_OPTION);

		// Option pane returns int of 1 or 0
		// Yes = 0, no = 1
		if (userOption == 0) {
			boolToReturn = true;
		}

		return boolToReturn;
	}

	public static void main(String[] args)

	{
		// TODO Auto-generated method stub

		// Initialized Project class
		Project project = new Project(0, "None", 0, 0, 0, "None", "None");
		Person person = new Person("None", "None", "None", 0, "None", "None", "None", "None", "None", 0);

		// Sets Main Menu Options
		String[] mainMenuOptions = { "Create New Project",
				"Change Due Date",
				"Change Total Paid To Date",
				"Update/Create Contact Profile",
				"Finalize Project"
		};

		// Boolean with the value of true
		Boolean isAwaitingInput = true;

		// Boolean with the value of false
		Boolean isCreatingNewProject = false;

		// While loop to run nothing is entered
		while (isAwaitingInput) {

			String menuSelection = (String) JOptionPane.showInputDialog(
					null,
					"Please Select Your Task:\n\n",
					"Poised Project Management",
					JOptionPane.PLAIN_MESSAGE,
					null, mainMenuOptions,
					"Create New Project");

			// Close Application
			if (menuSelection == null) {

				if (confirmUserChoice("Are you sure you want to close the app?")) {
					isAwaitingInput = false;
				}

				// Create New Project
			}

			// Create New Project
			else if (menuSelection.equals(mainMenuOptions[0])) {
				isCreatingNewProject = true;

				while (isCreatingNewProject) {
					System.out.println("\nYou selected " + mainMenuOptions[0]);
					// Sets the Architect that will be assigned to the project
					// Canceling returns user to main menu instantly
					int archAccNum = person.getAccountNumber("Architect");

					try {
						if (archAccNum <= 0) {
							isCreatingNewProject = false;
							break;
						}
					}

					catch (IllegalArgumentException i) {
						System.out.println("Account Number does not exist");
					}

					System.out.println("\nThe following contact has been created: \n" + person.toString(archAccNum));
					// Sets the Contractor that will be assigned to the project
					// Canceling returns user to main menu instantly
					int contAccNum = person.getAccountNumber("Contractor");

					try {
						if (contAccNum <= 0) {
							isCreatingNewProject = false;
							break;
						}
					}

					catch (IllegalArgumentException i) {
						System.out.println("Account Number does not exist");
					}

					System.out.println("\nThe following contact has been created: \n" + person.toString(contAccNum));
					// Sets the "customer that will be assigned to the project
					// Canceling returns user to main menu instantly
					int custAccNum = person.getAccountNumber("Customer");

					try {
						if (custAccNum <= 0) {
							isCreatingNewProject = false;
							break;
						}
					}

					catch (IllegalArgumentException i) {
						System.out.println("Account Number does not exist");
					}
					System.out.println("\nThe following contact has been created: \n" + person.toString(custAccNum));

					// Assigns the selected Architect, Contractor and Customer account numbers,
					project.setNewProject(archAccNum, contAccNum, custAccNum, person.getLastName(custAccNum));
					System.out.println("The follwoing new project has been created:\n" +
							project.toString(project.projectNum.length - 1,
									person.nameToString(archAccNum),
									person.nameToString(contAccNum),
									person.nameToString(custAccNum)));

					isCreatingNewProject = false;

				}

				// Change Due Date
			}

			else if (menuSelection.equals(mainMenuOptions[1])) {
				System.out.println("\nYou selected " + mainMenuOptions[1]);
				project.editProject("Due Date");
				// Change Total Paid To Date
			}

			else if (menuSelection.equals(mainMenuOptions[2])) {
				System.out.println("\nYou selected " + mainMenuOptions[2]);
				project.editProject("Amount Paid");

				// Update Contact's Profile Details
			}

			else if (menuSelection.equals(mainMenuOptions[3])) {
				System.out.println("\nYou selected " + mainMenuOptions[3]);
				String profileSelection = person.setCreateOrEdit();

				System.out.println(profileSelection);
				if (profileSelection.equals("Create New Contact Profile")) {
					int newAcc = person.getAccountNumber(Person.setPersonType());
					System.out.println("\nThe following contact has been created: \n" + person.toString(newAcc));

				}

				else if (profileSelection.equals("Edit Existing Contact Profile")) {
					person.editPersonAccount();
				}

				// Finalize Project
			}

			else if (menuSelection.equals(mainMenuOptions[4])) {
				System.out.println("\nYou selected " + mainMenuOptions[4]);
				project.editProject("Finalized");
			}
		}
	}
}
