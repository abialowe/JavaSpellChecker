package spellChecker;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import spellChecker.Misspell;

public class WordFinder extends SpellCheck{
    private int wordsLength;//length of String[] to check
    private ArrayList<String> wrongWords = new ArrayList<String>();//stores incorrect words

    /**
     * checks the String[] for spelling errors. 
     * Hashes each index in the String[] to see if it is in the dictionary HashSet
     */
    public void initialCheck(String[] words){
        wordsLength=words.length;

        System.out.println();
        for(int i=0;i<wordsLength;i++){
            if(!dictionary.contains(words[i])) wrongWords.add(words[i]);
        } //end for
        if (!wrongWords.isEmpty()) {
            System.out.println("Mistakes have been made!");
            printIncorrect();
        } //end if
        if (wrongWords.isEmpty()) {
            System.out.println("\n\nMove along. End of Program.");
        } //end if
    }//end of initialCheck

    /**
     * prints incorrect words in a String being checked and generates suggestions.
     */
    public void printIncorrect(){//delete this guy
        System.out.print("These words [ ");
        for (String wrongWord : wrongWords) {
            System.out.print(wrongWord + " ");
        }//end of for
        System.out.println("]seems incorrect.\n");
        suggest();
    }//end of printIncorrect

    /**
     * suggestions given to the user based on the wrong words misspelled.
     */
    public void suggest(){
        Misspell test = new Misspell();
        while(!wrongWords.isEmpty()&&test.possibilities.size()<=5){
            String wordCheck=wrongWords.remove(0);
            test.generateMispellings(wordCheck);
            //if the possibilities size is greater than 0 then print suggestions
            if(test.possibilities.size()>=0) test.print(test.possibilities);
        }//end of while
    }//end of suggest

    /**
     * allows a tester to look through the dictionary for words if they are valid
     */
    public void manualWordLookup(){
        System.out.print("Enter 'ext' to exit.\n\n");
        Scanner line = new Scanner(System.in);
        String look=line.nextLine();
        do{
        if(dictionary.contains(look)) System.out.print(look+" is valid\n");
        else System.out.print(look+" is invalid\n");
        look=line.nextLine();
        }while (!look.equals("ext"));
    }//end of manualWordLookup
}
