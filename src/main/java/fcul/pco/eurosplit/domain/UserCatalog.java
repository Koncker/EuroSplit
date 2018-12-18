package fcul.pco.eurosplit.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *The UserCatalog class stores instances of users
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */

public class UserCatalog {
	
	private Map<String,User> setUsers;
	private static UserCatalog instance;
	
	
	/**
	 * The constructor that initializes setUsers as a new HashMap that will contain a String and an object with type User 
	 */
	private UserCatalog () {
		this.setUsers = new HashMap<String,User>();
	}
	
	/**
	 * This method will see if there is an instance of User Catalog with the same name 
	 * and if it doesn't happen, create a new instance
	 * @return new instance of User Catalog
	 */
	
	public static UserCatalog getInstance() {
		if (instance == null) {
			instance = new UserCatalog();
		}
		return instance;
	}
	
	
	/**
	 * This method adds a given User to an array of users
	 * @param u a user
	 */
	
	public void addUser(User u) {
		setUsers.put(u.getEmail(), u);
	}
	
	/**
	 * This method gets an User by its id (its email) if it exists in the catalog.
	 * If there is no user in the catalog with that email returns null
	 * @param id is the user email
	 * @return an user 
	 */
	
	public User getUserById (String id) {
		
		if (setUsers.containsKey(id)) {
			return setUsers.get(id);
		}
		else {
			return null;
		}
		
	}
	
	
	/**
	 * This method will see if there is any User with that email in the User Catalog
	 * @param email
	 * @return true if there is an user with that email in the User Catalog and false if that doesn't happen
	 */
	
	public boolean hasUserWithId(String email) {
		//System.out.println(setUsers.containsKey(email));
		
		if (setUsers.containsKey(email)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * This method links this class with the class UserCatalog in the package persistence by its method save()
	 * @throws IOException
	 */
	
	public void save() throws IOException {
		fcul.pco.eurosplit.persistence.UserCatalog.save(setUsers);
	}
	
	/**
	 * This method links this class with the class UserCatalog in the package persistence by its method load()
	 * @throws FileNotFoundException
	 */
	
	public void load() throws FileNotFoundException {
		setUsers = fcul.pco.eurosplit.persistence.UserCatalog.load();
	}
	
	/**
	 * This method sorts the users by alphabetical order.
	 * @return a list with the users sorted
	 */
	
	public ArrayList <User> sortUsers() {
		
		ArrayList<User> list1 = new ArrayList<User>();
		list1.addAll(setUsers.values());
		Collections.sort(list1);
		
		//System.out.println(list1);
		return list1;
	}
	
	/**
	 * This method gets all users with a specified name.
	 * @param input name of the user
	 * @return all users with the input name
	 */
	
	public ArrayList<User> getUsersWithName (String input) {
		List<User> userList = sortUsers();
		ArrayList<User> users = new ArrayList<User>();
		for (User u: userList) {
			if (input.equals(u.getName())) {
				users.add(u);
			}
		}
		return users;
	}
	
	/**
	 * This method gets all the names of the users.
	 * @return list of string with all the names of the users
	 */
	public List<String> tableUserNames() {
		List<User> list2 = sortUsers();
		List<String> listNames = new ArrayList<>();
		
		for (User u: list2) {
			ArrayList<String> names = new ArrayList<>();
			names.add(u.getName().toLowerCase());
			listNames.addAll(names);
		}
		
		return listNames;
	}
	
	/**
	 * This method gets all users in a table.
	 * @return a table of users
	 */
	
	public ArrayList<List<String>> tableUsers() {
		ArrayList<User> list1 = sortUsers();
		ArrayList<List<String>> sortedUsers = new ArrayList<>();
		
		for (User u: list1) {
			ArrayList<String> users = new ArrayList<>();
			users.add(u.getName());
			users.add(u.getEmail());
			sortedUsers.add(users);
		}
		return sortedUsers;
		}
		
	}
	

