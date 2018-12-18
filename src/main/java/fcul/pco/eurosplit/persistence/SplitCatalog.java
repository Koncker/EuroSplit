package fcul.pco.eurosplit.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.eurosplit.domain.Split;
import fcul.pco.eurosplit.domain.User;
import fcul.pco.eurosplit.main.ApplicationConfiguration;
import fcul.pco.eurosplit.main.Start;

/**
 *The SplitCatalog class links the package persistence with the package domain
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */

public class SplitCatalog {
		
		/**
		 * This method writes in a file all the splits that are in the catalog
		 * @param splits all the splits in the catalog 
		 * @throws IOException
		 */
		
		public static void save (Map<User,List<Split>> splits) throws IOException  {
			BufferedWriter bw = new BufferedWriter (new FileWriter (ApplicationConfiguration.ROOT_DIRECTORY
					+"/"+ApplicationConfiguration.SPLIT_CATALOG_FILENAME));
			for (List<Split> ls : splits.values()) {
				for (Split s : ls) {
					bw.write(s.toString());
					bw.newLine();
				}
			}
			bw.close();	
		}
		
		
		/**
		 *This method reads a file and converts all the information read in a object with type Split and put it all inside the catalog 
		 * @return a SplitCatalog with all the splits in the file 
		 * @throws FileNotFoundException 
		 */
		
		public static Map<User,List<Split>> load() throws FileNotFoundException  {
			Map<User,List<Split>> listOfSplits = new HashMap<User,List<Split>>();
			Scanner inputFromFile = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
					+"/"+ApplicationConfiguration.SPLIT_CATALOG_FILENAME));
			while (inputFromFile.hasNextLine()) {
				Split s = Split.fromString(inputFromFile.nextLine());
				if (listOfSplits.containsKey(s.getOwner())) {
					listOfSplits.get(s.getOwner()).add(s);
				} else {
					ArrayList<Split> splits = new ArrayList<>();
					splits.add(s);
					listOfSplits.put(s.getOwner(), splits);
				}
			}
			
			inputFromFile.close();
			return listOfSplits;
		}
	}
