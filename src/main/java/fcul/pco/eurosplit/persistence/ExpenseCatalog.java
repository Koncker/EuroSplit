package fcul.pco.eurosplit.persistence;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Expense;
import fcul.pco.eurosplit.main.ApplicationConfiguration;

/**
 *The UserCatalog class links the package persistence with the package domain
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */

public class ExpenseCatalog {

	/**
	 * This method writes in a file all the expenses that are in the catalog
	 * @param expenses represents all the expenses in the catalog 
	 * @throws IOException
	 */
	
	public static void save (Map<Integer,Expense> expenses) throws IOException  {
		BufferedWriter bw = new BufferedWriter (new FileWriter (ApplicationConfiguration.ROOT_DIRECTORY
				+"/"+ApplicationConfiguration.EXPENSES_CATALOG_FILENAME));
		for (Expense e : expenses.values()  ) {
			bw.write(e.toString());
			bw.newLine();
		}
		bw.close();	
	}
	
	/**
	 *This method reads a file and converts all the information read in a object with type Expense and put it all inside the catalog 
	 * @return a ExpenseCatalog with all the expenses in the file 
	 */
	
	public static Map<Integer,Expense> load()  {
		Map<Integer,Expense> expenses = new HashMap<Integer,Expense>();
		try (Scanner inputFromFile = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
				+"/"+ApplicationConfiguration.EXPENSES_CATALOG_FILENAME))){
		while (inputFromFile.hasNextLine()) {
			Expense e = Expense.fromString(inputFromFile.nextLine());
			expenses.put(e.getId(), e);
		}
		}
		catch (FileNotFoundException exception) {
			//if the file is not found it will return an empty user catalog
		}
				
		return expenses;
		
		
	}
	
}
