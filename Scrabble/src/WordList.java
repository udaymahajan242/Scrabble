/** ------------------------------------------------------------------------ 
 * WordList.java
 *       This class will read in wordlist
 *      from a existing text file and will allow looking up words.
 *
 * ----------------------------------------------------------------------------
 */


import java.util.Scanner;

import java.util.ArrayList;   
import java.io.*;               
 


public class WordList
{
    // Create an String ArrayList  for the wordlist which can hold any number of words.
 
	
    ArrayList<String> wordlist;
    
    
    /** WordList() Constructor
     *      Default constructor
     */
    public WordList() throws IOException
    {
       // Create and initialize wordlist
            	
        wordlist = new ArrayList<String>();
        
        // Fill the array list with words from the wordlist file
        
        readInDictionaryWords();        
    }//end WordList constructor
    
    
    //read the wordlist text file
    
    public void readInDictionaryWords() throws IOException
    {
    	
    	//Scanner will read a text file containing all words.
    	
       
    	
//    	String pathname = (new File("wordlist.txt").getAbsolutePath());
    	
        File dictionaryFile = new File("wordlist.txt");    // declare the file
        // ensure file exists and is in the correct directory
        
        if( !dictionaryFile.exists()) {
//            System.out.println("File does not exist");
//            System.exit(-1);    
        	throw new IOException();
        }
        
        Scanner dictFile = new Scanner( dictionaryFile);
        
        // while there are words in the wordlist text file, add them to the wordlist, 
        // converting them to upper case as the user values will be upper case too.
        
        while( dictFile.hasNext()) {
            wordlist.add( dictFile.nextLine().toUpperCase() );
        }
        dictFile.close();
    }//end readInDictionaryWords()
    
    
    /** wordExists()
     *      Allow looking up a word in wordlist, returning a value of 
     *      true or false
     */
    public boolean wordExists( String findWord)
    {
        if( wordlist.contains( findWord)) {
            return true;    // word was found in wordlist file
        }
        else {
            return false;   // word was not found in wordlist file    
        }
    }
    
    
}
