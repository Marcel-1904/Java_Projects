package CapstoneProject1;

//To give the user a gui
import javax.swing.JOptionPane;

public class Person {
	// First to create the attributes for the contractor, customer
	// and architect
	String[] personType;
	String[] firstName;
	String[] lastName;
	int[] phoneNumber;
	String[] email;
	String[] street;
	String[] city;
	String[] region;
	String[] country;
	int[] postalCode;
	int[] accountNum;

	public Person(String personType,
			String firstName,
			String lastName,
			int phoneNumber,
			String email,
			String street,
			String city,
			String region,
			String country,
			int postalCode)

	{
		// To append all the information to arrays
		this.personType = Project.addToStringArray(this.personType, personType);
		this.firstName = Project.addToStringArray(this.firstName, firstName);
		this.lastName = Project.addToStringArray(this.lastName, lastName);
		this.phoneNumber = Project.addToIntArray(this.phoneNumber, phoneNumber);
		this.email = Project.addToStringArray(this.email, email);
		this.street = Project.addToStringArray(this.street, street);
		this.city = Project.addToStringArray(this.city, city);
		this.region = Project.addToStringArray(this.region, region);
		this.country = Project.addToStringArray(this.country, country);
		this.postalCode = Project.addToIntArray(this.postalCode, postalCode);
		this.accountNum = Project.addToIntArray(this.accountNum, 1);
	}

	public String toString(int accNum) {
		// An open string
		String output = "";

		for (int index = 0; index < this.accountNum.length; index++) {
			if (this.accountNum[index] == accNum) {
				output += "\nContact Type: " + this.personType[index];
				output += "\nContact Fistname: " + this.firstName[index];
				output += "\nContact Lastname: " + this.lastName[index];
				output += "\nContact Phone Number: " + this.phoneNumber[index];
				output += "\nContact Email Address: " + this.email[index];
				output += "\nContact Street Address: " + this.street[index];
				output += "\nContact City: " + this.city[index];
				output += "\nContact Province: " + this.region[index];
				output += "\nContact Country: " + this.country[index];
				output += "\nContact Postal Code: " + this.postalCode[index];
			}

		}
		return output;
	}

	// Return the first and last name of the person who's number
	// is passed. The values are both returned as a single string
	public String nameToString(int accNum) {
		String output = "";

		for (int index = 0; index < accountNum.length; index++) {
			if (this.accountNum[index] == accNum) {
				output += this.firstName[index] + " ";
				output += this.lastName[index];
			}

		}
		return output;
	}

	// Method used to display all accounts of the same type of a list
	//
	//
	//
	public int[] getAccTypeIndex(String[] accountType, String accountTypeNeeded) {
		int[] accountArrayIndex;
		int accountTypeCounter = 0;

		for (int index = 0; index < accountType.length; index++) {
			if (accountType[index].equals(accountTypeNeeded)) {
				accountTypeCounter++;
			}
		}

		if (accountTypeCounter > 0) {
			accountArrayIndex = new int[accountTypeCounter];
			int newArrayIndexCounter = 0;

			for (int typeIndex = 0; typeIndex < accountType.length; typeIndex++) {
				if (accountType[typeIndex].equals(accountTypeNeeded)) {
					accountArrayIndex[newArrayIndexCounter] = typeIndex;
					newArrayIndexCounter++;
				}
			}
		}

		else {
			// Set's variable to identify if array is empty
			// Is arrays are empty -1 is returned to result in an error prompting the user
			// to first create an account
			accountArrayIndex = new int[1];
			accountArrayIndex[0] = -1;
		}
		return accountArrayIndex;
	}

	// Method used to return the contact last name based on the
	// passed account number this is used when creating
	// default project names
	public String getLastName(int accNum) {
		String lastNameToReturn = "";

		for (int index = 0; index < this.accountNum.length; index++) {
			if (accNum == this.accountNum[index]) {
				lastNameToReturn = this.lastName[index];
			}
		}
		return lastNameToReturn;

	}

	// Returns a string based on the user selection
	// This method is used to assign a contact type to each person's account
	public static String setPersonType() {
		String[] personOptions = { "Architect",
				"Contractor",
				"Customer"
		};

		String personSelection = (String) JOptionPane.showInputDialog(
				null,
				"Please Select The Account Type:\n\n",
				"Poised Project Management",
				JOptionPane.PLAIN_MESSAGE,
				null, personOptions,
				"Architect");

		if (personSelection == null) {

			if (projectManagmentSystem.confirmUserChoice("Are you sure you want to cancel selection?")) {
				personSelection = "";
			}
		}
		return personSelection;
	}

	// Method is used to return a user selection of creating a new contact or
	// editing an existing one
	// The method only allows the user the option to select editing an account if at
	// least 1 contact has been created
	public String setCreateOrEdit() {
		String[] personOptions = { "Create New Contact Profile" };
		// Add the option to edit an existing contact if at least 1 contact has been
		// created
		if (!this.firstName[0].equals("None")) {
			personOptions = Project.addToStringArray(personOptions, "Edit Existing Contact Profile");
		}
		String personSelection = (String) JOptionPane.showInputDialog(
				null,
				"Please Select Your Task:\n\n",
				"Poised Project Management",
				JOptionPane.PLAIN_MESSAGE,
				null, personOptions,
				"Create New Project");

		if (personSelection == null) {
			personSelection = "";
		}
		return personSelection;
	}

	// Method used to set account numbers of newly created account
	// Account number is always 1 digit higher than the last account number on the
	// array
	private void setAccountNum(int[] accountNum) {
		// Set's value to be 1 higher than the last account created
		// This ensures a unique number for every account created
		int newAccountNum = accountNum[accountNum.length - 1] + 1;
		this.accountNum = Project.addToIntArray(this.accountNum, newAccountNum);

	}

	// Method used to edit account info
	// The user is requested to select an account type and then the desired account
	// of that set type
	// If no accounts of that specific type exist, the user is first requested to
	// create a new account of that type
	// If not the user is asked to set all the different parameters of that person's
	// account
	// The account details are only set after all parameters have been entered
	// The account number is not changed because this always needs to point to the
	// same account
	public void editPersonAccount() {
		// The user is asked to select an account type
		String personTypeToSearch = setPersonType();
		// If blank entry is returned it means the user canceled the selection
		// The method is then left without making changes
		if (personTypeToSearch.isBlank()) {
			return;
		}

		int selectionIndex = 0;
		int[] accountTypeArray = getAccTypeIndex(this.personType, personTypeToSearch);
		String[] accountSelectArray = new String[accountTypeArray.length];
		// If no accounts of the selected type exist the user is asked to create a new
		// account of that type
		// If they select yes the account time is passed to the setNewPerson method for
		// creation
		if (accountTypeArray[0] == -1) {

			if (projectManagmentSystem.confirmUserChoice("No " + personTypeToSearch +
					" Accounts currently exist.\n" +
					"Would you like to create a new contact instead?")) {
				// Creates new contact is the user selects yes
				setNewPerson(personTypeToSearch);
			}

			// A new temp array is created to display all the available accounts for the
			// selected account type
		}

		else {
			for (int index = 0; index < accountTypeArray.length; index++) {

				// the account values are passed to an new array that displays them all in a
				// list
				accountSelectArray[index] = this.firstName[accountTypeArray[index]] + " " +
						this.lastName[accountTypeArray[index]] +
						" - " + this.personType[accountTypeArray[index]];
			}
			// All available accounts are displayed for selection in a drop down box
			String menuSelection = (String) JOptionPane.showInputDialog(
					null,
					"Please Select An Account:\n\n",
					"Poised Project Management",
					JOptionPane.PLAIN_MESSAGE,
					null, accountSelectArray,
					accountSelectArray[0]);

			// The user cancels the selection process they are asked if they want to return
			// to the main menu
			// Otherwise they are looped through the process of updating all the account
			// details
			if (menuSelection == null) {
				if (projectManagmentSystem
						.confirmUserChoice("Are you sure you want to cancel entry and return to main menu?")) {
					return;
				}

			}

			else {
				for (int value = 0; value < accountSelectArray.length; value++) {
					if (menuSelection.equals(accountSelectArray[value])) {

						selectionIndex = accountTypeArray[value];
					}
				}

				// Now to take inputs for all information needed for every attribute from the
				// user
				String tempPersonType = setPersonType();
				if (tempPersonType.isBlank()) {
					return;
				}
				String tempFirstName = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s firstname",
						"You need to enter their firstname in order to continue");
				if (tempFirstName.isBlank()) {
					return;
				}
				String tempLastName = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s lastname",
						"You need to enter their lastname in order to continue");
				if (tempLastName.isBlank()) {
					return;
				}
				// add condition to check if number is 10 digits long
				int tempPhoneNumber = projectManagmentSystem.getValidInt(
						"Please enter the " + tempPersonType + "'s Phone Number",
						"You need to enter their Phone Number in order to continue");
				if (tempPhoneNumber == -1) {
					return;
				}
				String tempEmail = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s Email",
						"You need to enter their Email in order to continue");
				if (tempEmail.isBlank()) {
					return;
				}
				String tempStreet = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s street address",
						"You need to enter their street address in order to continue");
				if (tempStreet.isBlank()) {
					return;
				}
				String tempCity = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s current City",
						"You need to enter their current City in order to continue");
				if (tempCity.isBlank()) {
					return;
				}
				String tempRegion = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s current Province",
						"You need to enter their current Province in order to continue");
				if (tempRegion.isBlank()) {
					return;
				}
				String tempCountry = projectManagmentSystem.getUserString(
						"Please enter the " + tempPersonType + "'s current Country",
						"You need to enter their current Country in order to continue");
				if (tempCountry.isBlank()) {
					return;
				}
				int tempPostalCode = projectManagmentSystem.getValidInt(
						"Please enter the " + tempPersonType + "'s Postal Code",
						"You need to enter their Postal Code in order to continue");
				if (tempPostalCode == -1) {
					return;
				}

				// Profile is only updated at the end if the used doesn't cancel halfway through
				// the process
				this.personType[selectionIndex] = tempPersonType;
				this.firstName[selectionIndex] = tempFirstName;
				this.lastName[selectionIndex] = tempLastName;
				// Add condition to check if number is 10 digits long
				this.phoneNumber[selectionIndex] = tempPhoneNumber;
				this.email[selectionIndex] = tempEmail;
				this.street[selectionIndex] = tempStreet;
				this.city[selectionIndex] = tempCity;
				this.region[selectionIndex] = tempRegion;
				this.country[selectionIndex] = tempCountry;
				this.postalCode[selectionIndex] = tempPostalCode;
				// Account number is skipped because it should not be edited to always point
				// towards the same person's account
			}
		}
	}

	// This method is used to locate an account number based on the selected account
	// type
	// If no account is found matching that account type, the user is asked if they
	// would like to create a new one
	// If the specified account type exists, the user is given a list of accounts to
	// select from
	// After the user selects an account, the index of that account is passed and
	// each value is updated / edited
	public int getAccountNumber(String accType) {
		int accNumber = 0;

		String personTypeToSearch = accType;
		// If the user cancels the method returns -1 to signal that the process was
		// canceled
		if (personTypeToSearch.isBlank()) {
			return -1;
		}
		int selectionIndex = 0;
		// A temp array is created with all the index numbers of the selected account
		// type
		int[] accountTypeArray = getAccTypeIndex(this.personType, personTypeToSearch);
		String[] accountSelectArray = new String[accountTypeArray.length];
		// If the getAccTypeIndex returns -1, it means the array is empty and the user
		// is asked to create a new account
		if (accountTypeArray[0] == -1) {
			// Creates new contact is the user selects yes
			boolean createNewAcc = projectManagmentSystem.confirmUserChoice("No " + personTypeToSearch +
					" Accounts currently exist.\n" +
					"Would you like to create a new contact instead?");

			if (createNewAcc) {
				int newAccount = setNewPerson(personTypeToSearch);
				if (newAccount == 1) {
					accNumber = this.accountNum[this.accountNum.length - 1];
				}
			}

			else if (!createNewAcc) {
				return -1;
			}

			// If the array is not empty the user is shown a list of all account with the
			// specified account type
		}

		else {
			for (int index = 0; index < accountTypeArray.length; index++) {

				// The account values are passed to an new array that displays them all in a
				// list
				accountSelectArray[index] = this.firstName[accountTypeArray[index]] + " " +
						this.lastName[accountTypeArray[index]] +
						" - " + this.personType[accountTypeArray[index]];
			}
			String menuSelection = (String) JOptionPane.showInputDialog(
					null,
					"Please Select the " +
							personTypeToSearch + "\n\n",
					"Poised Project Management",
					JOptionPane.PLAIN_MESSAGE,
					null, accountSelectArray,
					accountSelectArray[0]);

			// If the user cancels the method returns -1 to signal that the process was
			// canceled
			if (menuSelection == null) {
				accNumber = -1;

			}

			else {
				// Alternatively the array is looped over and the account number matching the
				// user selection is returned
				for (int value = 0; value < accountSelectArray.length; value++) {
					if (menuSelection.equals(accountSelectArray[value])) {
						selectionIndex = accountTypeArray[value];
						accNumber = this.accountNum[selectionIndex];
					}
				}
			}
		}
		return accNumber;
	}

	// Method creates a new contact
	// The method asks the user to enter all the parameters for the contact
	// If no accounts have been created the values are added to the 0 index
	// Alternatively the values are all appended to the existing arrays
	// The method returns -1 to indicate the user canceled the process
	// The method returns the account number after the account was successfully
	// created
	public int setNewPerson(String accountType) {

		// Creates temp variables to be appended after all variable as successfully
		// entered without the user canceling
		String tempPersonType = accountType;
		if (tempPersonType.isBlank()) {
			return -1;
		}

		String tempFirstName = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s firstname",
				"You need to enter their firstname in order to continue");

		if (tempFirstName.isBlank()) {
			return -1;
		}

		String tempLastName = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s lastname",
				"You need to enter their lastname in order to continue");

		if (tempLastName.isBlank()) {
			return -1;
		}

		// Add condition to check if number is 10 digits long
		int tempPhoneNumber = projectManagmentSystem.getValidInt(
				"Please enter the " + tempPersonType + "'s Phone Number",
				"You need to enter their Phone Number in order to continue");

		if (tempPhoneNumber == -1) {
			return -1;
		}

		String tempEmail = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s Email",
				"You need to enter their Email in order to continue");

		if (tempEmail.isBlank()) {
			return -1;
		}

		String tempStreet = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s street address",
				"You need to enter their street address in order to continue");

		if (tempStreet.isBlank()) {
			return -1;
		}

		String tempCity = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s current City",
				"You need to enter their current City in order to continue");

		if (tempCity.isBlank()) {
			return -1;
		}

		String tempRegion = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s current Province",
				"You need to enter their current Province in order to continue");

		if (tempRegion.isBlank()) {
			return -1;
		}

		String tempCountry = projectManagmentSystem.getUserString(
				"Please enter the " + tempPersonType + "'s current Country",
				"You need to enter their current Country in order to continue");

		if (tempCountry.isBlank()) {
			return -1;
		}

		int tempPostalCode = projectManagmentSystem.getValidInt(
				"Please enter the " + tempPersonType + "'s Postal Code",
				"You need to enter their Postal Code in order to continue");

		if (tempPostalCode == -1) {
			return -1;
		}

		// Variables are set to the 0 index if the current arrays are empty
		if (this.personType[0].equals("None")) {

			this.firstName[0] = tempFirstName;
			this.lastName[0] = tempLastName;
			this.phoneNumber[0] = tempPhoneNumber;
			this.email[0] = tempEmail;
			this.street[0] = tempStreet;
			this.city[0] = tempCity;
			this.region[0] = tempRegion;
			this.country[0] = tempCountry;
			this.postalCode[0] = tempPostalCode;
			this.personType[0] = tempPersonType;
			this.accountNum[0] = 1;
			// Account number is skipped for first instance, due to first account number
			// being set to 1 by default
			return this.accountNum[0];
			// Values are appended to existing arrays if the arrays are currently not set to
			// "none"
		}

		else {

			this.personType = Project.addToStringArray(this.personType, tempPersonType);

			this.firstName = Project.addToStringArray(this.firstName, tempFirstName);

			this.lastName = Project.addToStringArray(this.lastName, tempLastName);

			this.phoneNumber = Project.addToIntArray(this.phoneNumber, tempPhoneNumber);

			this.email = Project.addToStringArray(this.email, tempEmail);

			this.street = Project.addToStringArray(this.street, tempStreet);

			this.city = Project.addToStringArray(this.city, tempCity);

			this.region = Project.addToStringArray(this.region, tempRegion);

			this.country = Project.addToStringArray(this.country, tempCountry);

			this.postalCode = Project.addToIntArray(this.postalCode, tempPostalCode);

			// Adds new account number to account number list
			setAccountNum(this.accountNum);
			return this.accountNum[this.accountNum.length - 1];
		}
	}
}
