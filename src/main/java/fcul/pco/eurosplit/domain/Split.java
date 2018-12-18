package fcul.pco.eurosplit.domain;

import java.util.ArrayList;

import fcul.pco.eurosplit.main.Start;

/**
 *The Split class represents the set of expenses associated with an event
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */
public class Split {
	
	private User owner;
	private int id;
	private String event;
	private final ArrayList<Expense> expenses;
	
	/**
	 * 
	 * A constructor that initializes the owner, the id, the event and the expenses of that event
	 * @param id the id of the event 
	 * @param owner the owner of the event
	 * @param event the event
	 * @param expenses all the expenses associated with the event
	 *
	 */
	
	public Split(int id, User owner, String event, ArrayList<Expense> expenses ) {
		this.id = id;
		this.owner = owner;
		this.event = event;
		this.expenses = expenses;
		
	}
	
	
	/**
	 * 
	 * @param event the event to set
	 */
	public void setEvent (String event) { 
		this.event = event;
	}
	
	public String getEvent () {
		return event;
	}
	
	/**
	 * 
	 * @return the owner of the event
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * 
	 * @return the owner of the event
	 */
	public ArrayList<Expense> getExpense() {
		return expenses;
	}
	
	/**
	 * This method adds an expense to the event
	 * @param e the expense to add
	 * @return a set of expenses
	 */
	public void addExpense (Expense e) {
		expenses.add(e);
	}
	
	
	/**
	 * Textual representation of this set of expenses associated with an event
	 * @return a String event and the expenses associated
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("#");
		sb.append(getOwner().getEmail());
		sb.append("#");
		sb.append(event);
		sb.append("#");
		for (Expense a:expenses) {
			sb.append(a.getId());
			sb.append(":");
		}
		return sb.toString();
	}
	
	/**
	 * This method receives the input from the user (a String) to return the type Split.
	 * @param s the input string
	 * @return a set of expenses associated with an event
	 */
	public static Split fromString (String s) {
		
		String [] ev = s.split("#");
		String id = ev[0];
		String emailOwner = ev[1];
		String event = ev[2];
		String expenses = ev[3];
		String[] expense = expenses.split(":");
		ArrayList<Expense> e = new ArrayList<Expense>();
		
		for (int i=0; i < expense.length ;i++) {
			Expense ex = Start.getExpenseCatalog().getExpenseById(Integer.parseInt(expense[i]));
			e.add(ex);
		}	
		
		Split sp = new Split (Integer.parseInt(id), Start.getUserCatalog().getUserById(emailOwner), event, e);
		return sp;
	}
	
	
	

}
