package fcul.pco.eurosplit.domain;

import java.util.ArrayList;

import fcul.pco.eurosplit.main.Start;

/**
 *The Expense class represents an expense composed by the purchase, the user, the value, the date and the payees
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */
public class Expense {

	private String purchase;
	private User paidBy;
	private double value;
	private Date purchase_date;
	private ArrayList<User>paidFor;
	private static int counter;
	private int id;
	
	/**
	 * 
	 * A constructor that initializes the purchase, the user, the value, the date and the payers of a new object
	 * @param purchase the purchase of the expense
	 * @param paidBy the user that made the expense 
	 * @param value the value of the expense
	 * @param purchase_date the date of the expense
	 * @param paidFor all the payees that made the expense
	 * 
	 */
	
	public Expense(String purchase, User paidBy, double value, Date purchase_date, ArrayList<User> paidFor) {
		this.purchase = purchase;
		this.paidBy = paidBy;
		this.value = value;
		this.purchase_date = purchase_date;
		this.paidFor = paidFor;
		
	}
	
	
	/**
	 * 
	 * A second constructor that initializes the id, purchase, the user, the value, the date and the payers of a new object
	 * @param id the id of the expense
	 * @param purchase the purchase of the expense
	 * @param paidBy the user that made the expense 
	 * @param value the value of the expense
	 * @param purchase_date the date of the expense
	 * @param paidFor all the payees that made the expense
	 * 
	 */
	private Expense(int id, String purchase, User paidBy, double value, Date purchase_date, ArrayList<User> paidFor) {
		this.id=counter;
		this.purchase = purchase;
		this.paidBy = paidBy;
		this.value = value;
		this.purchase_date = purchase_date;
		this.paidFor = paidFor;
		counter = counter + 1;
		
	}
	
	/** 
	 * @return the id
	 */
	
	public int getId() {
		return id;
	}
	
	
	/** 
	 * @return the purchase done
	 */
	
	public String getPurchase() {
		return purchase;
	}

	/**
	 * @return the User that made the expense
	 */
	
	public User getUser() {
		return paidBy;
	}
	
	/**
	 * 
	 * @return the date of the expense
	 */
	
	public Date getDate() {
		return purchase_date;
	}
	
	/**
	 * 
	 * @return the value of the expense
	 */
	
	public double getValue() {
		return value;
	}
	
	public ArrayList<User> getPaidFor() {
		return paidFor;
	}
		
	
	/**
	 * Textual representation of this expense
	 * @return a String expense 
	 */
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" ");
		sb.append(getPurchase());
		sb.append(" ");
		sb.append(getUser().getEmail());
		sb.append(" ");
		sb.append(getValue());
		sb.append(" ");
		sb.append(getDate());
		sb.append(" ");
		for (User a:paidFor) {
			sb.append(a.getEmail());
			sb.append("!");
			
		}
		return sb.toString();
	}
	
	/**
	 * This method receives the input from the user (a String) to return the type Expense.
	 * @param e the input string
	 * @return a Expense
	 */
	public static Expense fromString (String e) {
		
		String [] expense = e.split(" ");
		String id = expense[0];
		String purchase = expense[1];
		String email = expense[2];
		String value = expense[3];
		String date = expense[4];
		String paidFor = expense[5];
		String[] payee = paidFor.split("!");
		ArrayList<User> p = new ArrayList<User>();
		
		for (int i=0; i < payee.length ;i++) {
			User u = Start.getUserCatalog().getUserById(payee[i]);
			p.add(u);
		}	
		
		Expense exp = new Expense (Integer.parseInt(id), purchase, Start.getUserCatalog().getUserById(email), Double.parseDouble(value), Date.fromString(date), p);
		return exp;
	}
	
	/**
	 * This method adds the payees to the expense.
	 * @param u the user to add
	 * @return an array list with the user add
	 */
	
	public void addPaidFor(User u) {
		paidFor.add(u);
	}
	
}
