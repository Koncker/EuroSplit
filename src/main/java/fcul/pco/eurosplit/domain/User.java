package fcul.pco.eurosplit.domain;

/**
 *The User class represents a user composed by name and email
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */

public class User implements Comparable <User>{

	private String name;
	private String email;
	
	/**
	 * A constructor that initializes the name and the email of a new object User.
	 * @param name the name of the user
	 * @param email the email of the user
	 */
	
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	/**
	 * @return the name of the user
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return the email of the user
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * Textual representation of this user
	 * @return the String User
	 */
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(" ");
		sb.append(getEmail());
		return sb.toString();
	}
	
	/**
	 * This method receives the input from the user (a String) to return the type User.
	 * @param s the input string
	 * @return an User
	 */
	public static User fromString (String u) {
		String [] user = u.split(" ");
		String name = (user[0]);
		String email = (user[1]);
		User us = new User (name, email);
		return us;
	}

	/**
	 * This method compares the email of user input with other user emails.
	 * @param other the other user
	 * @return true if they are equal and false if they are different
	 */
	@Override
	public int compareTo(User other) {
		return email.compareTo(other.email);
	}
}
