/**
 * C202 Assignment 4
 * TestLinkedList.java
 * Purpose: This program creates a spell checking “dictionary” then uses it to 
 * spell check a book.     
 *  
 * @author Dewey Kincheloe
 * @version 1.0 10/25/2016
 */


import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * The TestLinkedList class is used to control program execution for the 
 * MyAbstractList and MyLinkedList classes.
 *
 */
public class TestLinkedList 
{

/**
 * The removeSpecials method receives a word from the oliver text file and 
 * removes all special characters thus ensuring it is only composed of the 
 * letters a-z. If it is a word composed entirely of special characters, it is 
 * converted to an x so it will still count as a word to be checked.  
 *
 */
    public String removeSpecials(String word)
  {
      String cleanWord = "";
          for (int i=0;i<word.length();i++)
          {
              //Ascci range for a-z 97-122
              if (word.charAt(i)>96&&word.charAt(i)<123)
              {
                    cleanWord += word.charAt(i);
              }
          }
      if (cleanWord == "")
          cleanWord = "x";
      return cleanWord;
  }

/**
 * The main method controls program execution.   
 *
 */
  public static void main(String[] args) 
  {
    TestLinkedList obj = new TestLinkedList();  
    long wordsFound = 0;
    long wordsNotFound = 0;
    long compsFound = 0;
    long compsNotFound = 0;
    // Create a list for Linked Lists
    MyLinkedList[] list = new MyLinkedList[26];
    for(int i = 0; i< list.length; i++)
        {
            list[i] = new MyLinkedList<String>();
        }
    
    File f = new File ("random_dictionary.txt");
    String inputWord;
    try{	
	    Scanner in = new Scanner(f);
	    while ( in.hasNext())
            {
	        inputWord = in.next();
                inputWord = inputWord.toLowerCase();  
                int i = (inputWord.charAt(0)-97);
                list[i].addLast(inputWord);
	    }
	    System.out. println("Processing complete");
            in.close();
	}
	catch(IOException e)
        {
	    System.out.println("Unable to read file");
	}
    
    File book = new File ("oliver.txt");
    
    try{	
	    Scanner in = new Scanner(book);
	    while ( in.hasNext())
            {
	        inputWord = in.next();
                inputWord = inputWord.toLowerCase();  
                inputWord = obj.removeSpecials(inputWord);                
                int i = (inputWord.charAt(0)-97);
                
                int[] count = new int[1];
                count[0] = 0;
                if (list[i].contains(inputWord, count))
                {
                    wordsFound++;
                    compsFound += count[0];
                }
                else
                {
                    wordsNotFound++;
                    compsNotFound += count[0];
                }
	    }
	    System.out. println("Processing complete");
            in.close();
	}
	catch(IOException e)
        {
	    System.out.println("Unable to read file");
	}
    
    System.out.println("wordsfound " + wordsFound);
    System.out.println("wordsnotfound " + wordsNotFound);
    System.out.println("compsfound " + compsFound);
    System.out.println("compsnotfound " + compsNotFound);
    long avgcompswordsfound = compsFound / wordsFound;
    System.out.println("avgcompswordsfound " + avgcompswordsfound);
    float avgcompswordsnotfound = compsNotFound / wordsNotFound;
    System.out.println("avgcompswordsnotfound " + avgcompswordsnotfound);
  }

}

