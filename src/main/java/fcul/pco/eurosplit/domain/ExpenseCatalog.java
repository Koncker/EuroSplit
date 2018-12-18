package fcul.pco.eurosplit.domain;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *The ExpenseCatalog class stores instances of expenses
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */
public class ExpenseCatalog {
	
	private Map<Integer,Expense> setExpenses;
	private static ExpenseCatalog instance;
	
	
	/**
	 * The constructor that initializes setExpenses as a new HashMap that will contain an integer and an object with type Expense 
	 */
	private ExpenseCatalog() {
		this.setExpenses= new HashMap<Integer,Expense>();
	}
	
	/**
	 * This method will see if there is an instance of Expense Catalog with the same name 
	 * and if it doesn't happen, create a new instance
	 * @return new instance of Expense Catalog
	 */
	
	public static ExpenseCatalog getInstance() { 
		if (instance==null) {
			instance = new ExpenseCatalog();
		}
		return instance;
	}
	
	
	/**
	 * This method adds a given Expense to an array of expenses
	 * @param e a expense
	 */
	
	public void addExpense(Expense e) {
		setExpenses.put(e.getId(), e);
	}
	
	/**
	 * This method gets an expense by its id if it exists in the catalog.
	 * If there is no expense in the catalog with that id returns null
	 * @param id is the expense id
	 * @return an expense 
	 */
	
	
	public Expense getExpenseById (Integer id) {
	
		if (setExpenses.containsKey(id)) {
			return setExpenses.get(id);
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method will see if there is any Expense with that id in the Expense Catalog
	 * @param id from expense
	 * @return true if there is an expense with that id in the Expense Catalog and false if that doesn't happen
	 */
	
	public boolean hasExpenseWithId(Integer id) {
		if (setExpenses.containsKey(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method links this class with the class ExpenseCatalog in the package persistence by its method save()
	 * @throws IOException
	 */
	
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.ExpenseCatalog.save(setExpenses);
	}
	
	/**
	 * This method links this class with the class ExpenseCatalog in the package persistence by its method load()
	 * @throws FileNotFoundException
	 */
	
	public void load() throws FileNotFoundException {
		setExpenses = fcul.pco.eurosplit.persistence.ExpenseCatalog.load();
	}
}


