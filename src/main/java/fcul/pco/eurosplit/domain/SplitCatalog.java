package fcul.pco.eurosplit.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *The SplitCatalog class stores the set of splits
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */
public class SplitCatalog {
	
		private Map<User,List<Split>> setSplits;
		public static SplitCatalog instance;
		
		/**
		 * The constructor that initializes setSplits as a new HashMap that will contain a User and a list of his splits 
		 */
		private SplitCatalog() {
			this.setSplits = new HashMap<User,List<Split>>();
		}
		
		/**
		 * This method will see if there is an instance of Split Catalog with the same name 
		 * and if it doesn't happen, create a new instance
		 * @return new instance of Split Catalog
		 * @throws FileNotFoundException 
		 */
		
		public static SplitCatalog getInstance() {
			if (instance==null) {
				instance = new SplitCatalog();
			}
			return instance;
		}
		
		
		public void addSplit(User u, List<Split> splits) {
			setSplits.put(u, splits);
		}
		
				
		/**
		 * This method gets a split by its user if it exists in the catalog.
		 * If there is no split in the catalog with that user returns null
		 * @param currentUser is the split User
		 * @return a split 
		 */
		
		
		public List<Split> getUserSplits (User currentUser) {
			
			if (setSplits.containsKey(currentUser)) {
				return (List<Split>)setSplits.get(currentUser);
			}
			else {
				return null;
			}
		}
				
		/**
		 * This method links this class with the class ExpenseCatalog in the package persistence by its method save()
		 * @throws IOException
		 */
		
		public void save() throws IOException {
			fcul.pco.eurosplit.persistence.SplitCatalog.save(setSplits);
		}
		
		/**
		 * This method links this class with the class ExpenseCatalog in the package persistence by its method load()
		 * @throws FileNotFoundException
		 */
		
		public void load() throws FileNotFoundException {
			setSplits = fcul.pco.eurosplit.persistence.SplitCatalog.load();
		}
	}



