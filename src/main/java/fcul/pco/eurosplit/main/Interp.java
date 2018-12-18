package fcul.pco.eurosplit.main;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.SplitCatalog;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.domain.Date;
import fcul.pco.eurosplit.domain.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Interp class interacts with the user
 * @author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 * @version 4.7.3a
 * 
 */

public class Interp {
	
	
	/**
     * Contains the string that is correspond to interpreter's prompt. It is
     * printed on the console. The prompt is updated by the setPrompt() method.
     */
    private String prompt;
    
    /**
     * The input of the interpreter
     */
    private final Scanner input;
    
    /**
     * Contains the current user (after user creation or after login).
     */
    private User currentUser;

    /**
     * Contains the current Split
     */
    private Split currentSplit;
    
    /**
     * Contains the counter of the id
     */
    private static int counter;
    
    /**
	 * 
	 * A constructor that initializes the interp with the input
	 * @param prompt for user input
	 */
    public Interp (Scanner input) {
        prompt = ApplicationConfiguration.DEFAULT_PROMPT;
        this.input = input;
    }

    /**
     * Main interpreter command
     *
     * @param command
     * @param input
     * @throws IOException 
     */
    public void execute(String command, Scanner input) throws IOException {
        switch (command) {
            case "help":
	            help();
	            break;
            case "new user":
                makeNewUser(input);
                break;
            case "show users":
                showUsers();
                break;
            case "login":
                login(input);
                break;
            case "new split":
            	if (currentUser == null) {
                    System.out.println("Please login to use this command [" + command + "]");
                    break;
            	}
            	else {
	                makeNewSplit(input);
	                break;
            	}
            case "select split":
            	if (currentUser == null) {
                    System.out.println("Please login to use this command [" + command + "]");
                    break;
            	}
            	else {
	                selectSplit(input);
	                break;
            	}
            case "new expense":
            	if (currentUser == null) {
                    System.out.println("Please login to use this command [" + command + "]");
                    break;
            	}
            	else if (currentSplit == null) {
            		System.out.println("Please select a split to use this command [" + command + "]");
            	}
            	else {
	                makeNewExpense(input);
	                break;
            	}
/*            case "balance":
                printBalance();
                break;*/
            case "quit":
                quit();
                break;
            default:
                System.out.println("Unknown command. [" + command + "]");
        }
    }
	/**
	 * This method informs the user of the inputs available.
	 */
    private void help() {
        System.out.println("help: show commands information.");
        System.out.println("new user: create a new account.");
        System.out.println("show users: show the list of registred users.");
        System.out.println("new split: create a new split.");
        System.out.println("select split: select a split.");
        System.out.println("new expense: add an expense to current split.");
        System.out.println("balance: print the balance of the current split.");
        System.out.println("login: log a user in.");
        System.out.println("quit: terminate the program.");
    }

	/**
	 * This method allows the user to create a user.
	 * @param input for the user to create a user.
	 */    
    private void makeNewUser(Scanner input) throws IOException {
    	System.out.println("User name: ");
		String username = input.nextLine();
		System.out.println("E-mail address: ");
		String email = input.nextLine();

		if (Start.getUserCatalog().getUserById(email) != null) {//precisa de ver se já existe esse user
			System.out.println("Error! This email is already used.");
		}
		else { //se não existir esse user vai criar um novo
			User u = new User (username, email);
			Start.getUserCatalog().addUser(u);
			currentUser = u;
			System.out.println("User added successfully.");
			setPrompt();
			
		}
    }

	/**
	 * This method quits the application.
	 */    
    private void quit() {
        save();
    }

	/**
	 * This method shows all the users in the catalog.
	 */    
    private void showUsers() {
    	System.out.println(Table.tableToString(Start.getUserCatalog().tableUsers()));
    }
    
	/**
	 * This method allows a user to login to their account.
	 * @param input for the user to login.
	 */    
    private void login(Scanner input) {
        System.out.print("Username: ");
        String username = input.nextLine().toLowerCase(); //Isto garante que nao interessa se o utilizador meter tudo em caps, nos convertemos para comparacao
        
        if(Start.getUserCatalog().tableUserNames().contains(username)) {
            System.out.print("E-Mail: ");
            String email = input.nextLine().toLowerCase();
            
            if(Start.getUserCatalog().getUserById(email) != null) {
    			currentUser = Start.getUserCatalog().getUserById(email);
    			System.out.println("You have successfully logged in " + currentUser.getName() + " .");
    			setPrompt();
            }
            else {
            	System.out.print("No user with this e-mail.");
            	return;
            }
        }
        else {
	        System.out.println("Username not found");
	        setPrompt();
        }       		
    }

	/**
	 * This method allows a user to create a new split.
	 * @param input for the user to create a new split.
	 */    
	private void makeNewSplit(Scanner input) throws IOException { // Isto ainda nao adiciona nada ao ficheiro
		User owner = currentUser;
		System.out.print("For what event is this split?: ");
		String event = input.nextLine();
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		// SplitCatalog.getInstance().addSplit(currentUser, currentSplit); // aqui
		// deviamos adicionar simplesmente um split?
		int id;
		if (SplitCatalog.getInstance().getUserSplits(currentUser) == null) {
			try {
				id = SplitCatalog.getInstance().getUserSplits(currentUser).size() + 1;
			}
			catch (NullPointerException ex) {
				id = 1;
			}
			currentSplit = new Split(id, owner, event, expenses);
			List<Split> splits = new ArrayList<Split>();
			splits.add(currentSplit);
			SplitCatalog.getInstance().addSplit(currentUser, splits);
		} else {
			id = SplitCatalog.getInstance().getUserSplits(currentUser).size() + 1;
			currentSplit = new Split(id, owner, event, expenses);
			SplitCatalog.getInstance().getUserSplits(currentUser).add(currentSplit);
		}
		SplitCatalog.getInstance().save();
		setPrompt();
	}

	/**
	 * This method allows a user to select a split by a user.
	 * @param input for the user to select a split.
	 */    
    private void selectSplit(Scanner input) {
        System.out.print("Who is the owner of this split?: ");
        String splitNameInput = input.nextLine();
        User splitUser = selectUser(input, splitNameInput);
        List<Split> selectedUserSplits = new ArrayList<Split>();
        selectedUserSplits = SplitCatalog.getInstance().getUserSplits(splitUser);
        int k;
        
        if (selectedUserSplits == null) {
			System.out.println(splitUser.getName() + " Has no split yet.");
			setPrompt();
        }
        
        else if (selectedUserSplits.size() == 1) {
			System.out.println(selectedUserSplits.get(0));
			currentSplit = selectedUserSplits.get(0);
		}
		else if (!selectedUserSplits.isEmpty()) {
			for (int i = 0; i < selectedUserSplits.size(); i++) {
				System.out.println(i + " " + selectedUserSplits.get(i));
			}
			System.out.print("Select Split: ");
			k = Integer.parseInt(input.nextLine());
			currentSplit = selectedUserSplits.get(k);
			setPrompt();
		}
    }

	/**
	 * This method prints the balance for the current split.
	 */    
/*    private void printBalance() {
    	for (Expense e: currentSplit.getExpense()) {
    		e.getPaidFor();
    		e.getValue();
    		double paidValue = e.getValue() / e.getPaidFor().size();
    		System.out.println(e.getPaidFor().size());
    		List<User> Users = e.getPaidFor();
    		
    	}
    	

    }*/

	/**
	 * This method saves all the catalogs..
	 */    
    private void save() {
        try {
            Start.getUserCatalog().save();
        } catch (IOException ex) {
            System.err.println("Error saving User Catalog.");
        }
        try {
            Start.getExpenseCatalog().save();
        } catch (IOException ex) {
            System.err.println("Error saving Expense Catalog.");
        }
        try {
            SplitCatalog.getInstance().save();
        } catch (IOException ex) {
            System.err.println("Error saving Split Catalog.");
        }
    }

	/**
	 * This method allows a user to create an expense in a split.
	 * @param input for the user to make a new expense.
	 * @requires value to be an integer.
	 */    
    private void makeNewExpense(Scanner input) {
        System.out.print("Expense made by you " + currentUser.getName() + " What did you pay for? ");
        String expenseInput = input.nextLine();
        System.out.print("How much did you pay? ");
        
        int value = Integer.parseInt(input.nextLine());       
        ArrayList<User> paidFor = new ArrayList<User>();
        Expense e = new Expense(expenseInput, currentUser, value, Date.now(), paidFor); 
        
        while (true) {
                System.out.println("Who did you pay for: («no one» to terminate) ");
	        	String expenseNameInput = input.nextLine();
	        	if (expenseNameInput.equals("no one")) {
	        		break;
	        	}
	
	        	e.addPaidFor(selectUser(input, expenseNameInput));
        }
        
        System.out.println("Expense created");
        Start.getExpenseCatalog().addExpense(e);
        System.out.println("Expense added");
        currentSplit.addExpense(e);
        System.out.println("Added expense to currentSplit");
        System.out.println(currentSplit.getExpense());
        
//		SplitCatalog.getInstance().getUserSplits(currentUser).add(currentSplit);

    }

	/**
	 * This method gets the user input prompt
	 */    
    public String getPrompt() {
        return prompt;
    }

	/**
	 * This method sets the prompt and updates it.
	 */    
    public void setPrompt() {
        if (currentUser == null) {
            this.prompt = ApplicationConfiguration.DEFAULT_PROMPT;
        }
        else if (currentSplit == null) {
            this.prompt = currentUser.getName();
        }
        else {
            this.prompt = currentUser.getName() + "." + currentSplit.getEvent();
        }
         
    }

    String nextToken() {
        String in;
        System.out.print(prompt + "> ");
        System.out.flush();
        if (input.hasNextLine()) {
            in = input.nextLine();
            return in;
        } else {
            return "";
        }

    }

    /**
     * This method may be used to find a user in the catalog given its name, for 
     * example when we want to add "paidFor" users to an expense. The method 
     * receives a name. If there is only one user with this name, return that 
     * user. If there is no user with that name, give the opportunity to create 
     * a new user. The several users (with same name) are found, show the list 
     * and ask which one should be used.
     *
     * @param input
     * @param name
     * @return
     */
	private User selectUser(Scanner input, String name) {
		List<User> list = Start.getUserCatalog().getUsersWithName(name);
		if (list.size() == 1)
			return list.get(0);
		int k;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i + " " + list.get(i));
			}
			System.out.print("Select a user: ");
			k = Integer.parseInt(input.nextLine());
		} else {
			System.out.println("User not found.");
			System.out.print("Name: ");
			name = input.nextLine();
			return selectUser(input, name);
		}
		return list.get(k);
	}

	
    @SuppressWarnings("unused")
	private boolean askYNQuestion(Scanner input, String question) {
        System.out.print(question + "? (y/n):");
        String answer = input.nextLine();
        while (!(answer.equalsIgnoreCase("Y")
                || answer.equalsIgnoreCase("N"))) {
            System.out.print(question + "? (y/n):");
            answer = input.nextLine();
        }
        return answer.equalsIgnoreCase("Y");
    }

}

