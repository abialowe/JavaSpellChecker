package spellChecker;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Main class that generates misspellings.
 */
public class Misspell extends SpellCheck{
    public List possibilities = new ArrayList<String>();//stores possible suggestions
    private List<String> tempHolder = new ArrayList<String>(); 
    private String wrongWord;// the original wrong word.

    /**
     * Execute methods that make misspellings.
     */
    public void generateMispellings(String wordCheck){
        wrongWord=wordCheck;
        try{
            concatFL(wordCheck);
            concatLL(wordCheck);
            replaceFL(wordCheck);
            replaceLL(wordCheck);
            deleteFL(wordCheck);
            deleteLL(wordCheck);
            pluralize(wordCheck);
            transposition(wordCheck);
        }catch(StringIndexOutOfBoundsException e){ 
            System.out.println();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println();
        }
    }

    /**
     * concat. word behind each of the alphabet letters//checks if it is in the dictionary. 
     * FL==first letter
     */
    public void concatFL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord+=cur;
            //if the word is in the dictionary then add it to the possibilities list
            tempWord=tempWord.concat(word); //add passed String to end of tempWord
            checkDict(tempWord); //check to see if in dictionary
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of concatFL

    /**
     * concat. alphabet letters behind each of the word and checks if it is in the dictionary. 
     * LL==last letter.
     */
    public void concatLL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=123;i>97;i--){
            cur=(char)i;//assign ASCII from index i value
            tempWord=tempWord.concat(word); //add passed String to end of tempWord
            tempWord+=cur;
            //if the word is in the dictionary then add it to the possibilities list
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of concatLL

    /**
     * Method replaces the first letter (FL) of a word with alphabet letters.
     */
    public void replaceFL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord=cur+word.substring(1,word.length()); //add the ascii of i ad the substring of the word from index 1 till the word's last index
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of replaceFL

    /**
     * This method replaces the last letter (LL) of a word with alphabet letters
     */
    public void replaceLL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord=word.substring(0,word.length()-1)+cur; //add the ascii of i ad the substring of the word from index 1 till the word's last index
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of replaceLL

    /**
     * deletes first letter and sees if it is in dictionary
     */
    public void deleteFL(String word){
        String tempWord=word.substring(1,word.length()-1); // stores temp made up word
        checkDict(tempWord);
        //print(possibilities);
    }//end of deleteFL

    /**
     * deletes last letter and sees if it is in dictionary
     */
    public void deleteLL(String word){
        String tempWord=word.substring(0,word.length()-1); // stores temp made up word
        checkDict(tempWord);
        //print(possibilities);
    }//end of deleteLL

    /**
     * pluralizes a word input
     */
    public void pluralize(String word){
        String tempWord=word+"s";
        checkDict(tempWord);
    }//end of pluralize

    /**
     * check a word if it is in the dictionary. 
     * If it is, then add it to the possibilities list.
     */
    public void checkDict(String tempWord){
        if(dictionary.contains(tempWord)){//check to see if tempWord is in dictionary
            //if the tempWord IS in the dictionary, then check if it is in the possibilities list 
            //then if tempWord IS NOT in the list, then add tempWord to list
            if(!possibilities.contains(tempWord)) possibilities.add(tempWord);
        }
    }//end of checkDict

    /**
     * This method transposes letters of a word into different places.
     * Not the best implementation. This guy was my last minute addition.
     * @param word the word being manipulated.
     */
    public void transposition(String word){
        wrongWord=word;
        int wordLen=word.length();
        String[] mixer = new String[wordLen]; //String[] length of the passed word
        //make word into String[]
        for(int i=0;i<wordLen;i++){
            mixer [i]=word.substring(i,i+1);
        }
        shift(mixer);
    }//end of transposition

    /**
     * takes a string[] list, shifts the value in between 
     * the elements in the list and checks if in dictionary, adds if so. 
     */
    public void shift(String[] mixer){
        System.out.println();
        String wordValue="";
        Object tempHolder;
		for(int i=0;i<=tempHolder.size();i++){
            resetHelper(tempHolder);//reset the helper
            transposeHelper(mixer);//fill tempHolder
            String wordFirstValue=tempHolder.remove(i);//remove value at index in tempHolder
            for(int j=0;j<tempHolder.size();j++){
                int inttemp=0;
                String temp;
                while(inttemp<j){
                    temp=tempHolder.remove(inttemp);
                    tempHolder.add(temp);
                    wordValue+=wordFirstValue+printWord(tempHolder);
                    inttemp++;
                    if(dictionary.contains(wordValue)) if(!possibilities.contains(wordValue)) possibilities.add(wordValue);
                    wordValue="";
                }//end of while
            }//end of for
        }//end for
    }//end of shift

    /**
     * fills a list tempHolder with contents from String[]
     */
    public void transposeHelper(String[] wordMix){
        for(int i=0;i<wordMix.length;i++){
            tempHolder.add(wordMix[i]);
        }
    }//end of transposeHelper

    /**
     * resets list
     */
    public void resetHelper(List<String> thisList){
        while(!thisList.isEmpty()) thisList.remove(0); //while list is not empty, remove first value
    }//end of resetHelper

    /**
     * method prints out a list
     */
    public void print(List<String> listPrint){
        if (possibilities.isEmpty()) {
            System.out.print("Can't seem to find any related words for "+wrongWord);
            return;
        }
        System.out.println("Maybe you meant these for "+wrongWord+": ");
        System.out.printf("%s", listPrint);
        resetHelper(possibilities);
    }//end of print

    /**
     * returns a String word version of list
     */
    public String printWord(List<String> listPrint){
        Object[] suggests = listPrint.toArray();
        String theWord="";
        for(Object word: suggests){//form listPrint elements into a word
            theWord+=word;
        }
        return theWord;
    }//end of printWord
}
