package fcul.pco.eurosplit.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Date;
import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.domain.ExpenseCatalog;
import fcul.pco.eurosplit.domain.SplitCatalog;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.domain.UserCatalog;

/**
 * The Start class it's responsible for the application launch
 * @author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 * @version 4.7.3a
 * 
 *
 */
public class Start {
    private static UserCatalog userCatalog;
    private static ExpenseCatalog expenseCatalog;
    private static SplitCatalog splitCatalog;

    
    
    /**
     * @return the catalog of users
     */
    public static UserCatalog getUserCatalog() {
    	return userCatalog;
    }
    
    /**
     * @return the catalog of expenses
     */
    
    public static ExpenseCatalog getExpenseCatalog() {
    	return expenseCatalog;
    }
    
    public static SplitCatalog getSplitCatalog() {
    	return splitCatalog;
    }
   

    /**
     * This method will load the catalogs
     */
    
    public static void initialize()   { 
    	splitCatalog = splitCatalog.getInstance();
    	userCatalog = userCatalog.getInstance(); 
    	expenseCatalog = expenseCatalog.getInstance();
    	try {
    		userCatalog.load();
    		expenseCatalog.load();
    		splitCatalog.load();
    	}
    	catch(IOException exception){
    		System.out.println("Error! Cannot load User Catalog!");
    		System.out.println("Error! Cannot load Expense Catalog!");
    		System.out.println("Error! Cannot load Split Catalog!");
    	}
    }
    /**
     * This method runs the application
     * @throws IOException
     */
    private static void run() throws IOException {
    	//deleteCatalogs();
    	Scanner input = new Scanner(System.in);
    	initialize();
    	Interp interp = new Interp(input);
    	String command = "";
    	do {
    		command = interp.nextToken();
    		interp.execute(command, input);
    	} while (!command.equals("quit"));
    	}
    
      
	public static void main(String[] args) throws IOException {
		
		run();	
		
		}
	/**
	 * Objetivos alcançados: etapa 1, 2 e 3 alcançados; Etapa 4 - Metodos (Help, New User, Show Users, Login, New Split, Select Split, New Expense, Quit)
	 * Falhas: Neste projeto não conseguimos implementar o metodo print balance e no metodo makeNewExpense nao conseguimos incrementar o ID da despesa. O ExpenseCatalog faz overwrite.
	 */
	
	}





