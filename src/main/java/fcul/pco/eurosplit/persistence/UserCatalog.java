package fcul.pco.eurosplit.persistence;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;

/**
 *The UserCatalog class links the package persistence with the package domain
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */

public class UserCatalog {
	
	/**
	 * This method writes in a file all the users that are in the catalog
	 * @param users all the users in the catalog 
	 * @throws IOException
	 */
	
	public static void save (Map<String,User> users) throws IOException  {
		BufferedWriter bw = new BufferedWriter (new FileWriter (ApplicationConfiguration.ROOT_DIRECTORY
				+"/"+ApplicationConfiguration.USER_CATALOG_FILENAME));
		for (User u : users.values()  ) {
			bw.write(u.toString());
			bw.newLine();
		}
		bw.close();	
	}
	
	/**
	 *This method reads a file and converts all the information read in a object with type User and put it all inside the catalog 
	 * @return a UserCatalog with all the users in the file 
	 */
	
	public static Map<String,User> load()  {
		Map<String,User> users = new HashMap<String,User>();
		try (Scanner inputFromFile = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
				+"/"+ApplicationConfiguration.USER_CATALOG_FILENAME))){
		while (inputFromFile.hasNextLine()) {
			User u = User.fromString(inputFromFile.nextLine());
			users.put(u.getEmail(), u);
		}
		}
		catch (FileNotFoundException exception) {
			//if the file is not found it will return an empty user catalog
		}
		return users;
		
		
	}
	

}
