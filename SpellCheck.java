package spellChecker;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import spellChecker.WordFinder;

/**
 * Main program that loading correct dictionary spellings 
 * taking input to be analyzed from user.
 * @Abi Lowe
 * 11/04/2016
 */
public class SpellCheck {
    private static String stringInput; // input to check;
    private static String[] checkThis; // the stringInput turned array of words to check.
    public static HashSet dictionary; // the dictionary used

    /**
     * Main method.
     */
    public static void main(String[] args) {
        setup();
    }//end of main
    
    /**
     * method loading the dictionary and initiates the checks for errors from input
     */
    public static void setup(){
        int tableSIZE=59000;
        dictionary = new HashSet(tableSIZE);
        try {
            //System.out.print(System.getProperty("user.dir"));//just to find user's working directory;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./lexicon.txt"));
            String line = null; // notes one line at a time
            while((line = bufferedReader.readLine()) != null) {
                dictionary.add(line);//add dictionary word in
            }
            prompt();
            bufferedReader.close();  
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
    }//end of setUp
    
    /**
     * Just a prompt for auto generated tests or manual input test.
     */
    public static void prompt(){
        System.out.println("Type a number from below: ");
        System.out.println("1.Enter a word to check its spelling\t2.Exit");
        Scanner theLine = new Scanner(System.in);
        int choice = theLine.nextInt(); 
        if(choice==1) startwInput();
        else if (choice==2) System.exit(0);
        else System.out.println("Invalid Input. Program Exiting.");
    }
    
    /**
     * Manual input of sentence or words.
     */
    public static void startwInput(){
        //printDictionary(bufferedReader); // print dictionary
        System.out.println("Enter text to check: ");
        Scanner theLine = new Scanner(System.in);
        stringInput = theLine.nextLine(); // for manual input
        System.out.print("\nThis is what you entered: "+stringInput+"\nStarting Check..."); 
        /*------------------------------------------------------------------------------------------------------------*/
    }//end of startwInput

    /**
     * prints the entire dictionary. 
     */
    public static void printDictionary(BufferedReader bufferedReader){
        String line = null; // notes one line at a time
        try{
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
    }//end of printDictionary
}