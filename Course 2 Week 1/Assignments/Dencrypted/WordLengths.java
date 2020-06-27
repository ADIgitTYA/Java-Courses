import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    /*
     * Write a void method countWordLengths that has two parameters,
       a FileResource named resource and an integer array named counts.
       This method should read in the words from resource and count
       the number of words of each length for all the words in resource, 
       storing these counts in the array counts.
     - For example, after this method executes, counts[k] should contain
       the number of words of length k.

     - If a word has a non-letter as the first or last character, 
       it should not be counted as part of the word length.
       For example, the word And, would be considered of length 3
       (the comma is not counted), the word “blue-jeans” would be 
       considered of length 10 
       (the double quotes are not counted, but the hyphen is).
       Note that we will miscount some words, such as “Hello,”
       which will be counted as 6 since we don’t count the double
       quotes but will count the comma, but that is OK as there should
       not be many words in that category.

     - For any words equal to or larger than the last index of the
       counts array, count them as the largest size represented in 
       the counts array.

     - You may want to consider using the Character.isLetter method 
       that returns true if a character is a letter, and false otherwise.
       For example, Character.isLetter(‘a’) returns true, and
       Character.isLetter(‘-’) returns false.
     */
    public int[] countWordLengths(FileResource resource , int[] counts) {
        for (String word: resource.words()) {
            int n = word.length();
            int len = 0;
            for (int i = 0; i < n; i++) {
                if (Character.isLetter(word.charAt(i)))  { len++; }
                if (!Character.isLetter(word.charAt(0))) { len--; }
                if (!Character.isLetter(word.charAt(word.length() -1))) { len--;}
            }
            if (len != 0 && len < counts.length) { counts[n] += 1; }
            else { System.out.println("The length of ARRAY counts is not enough!"); }
        }
        return counts;
    }
    
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int [] counts = new int[31];
        int [] result = countWordLengths(resource, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println(counts[i] + " word(s) of length " + (i + 1));
        }
        int maxlength = indexOfMax(result);
        System.out.println("The most common word length in the file is " + maxlength);        
    }
    
    /*
     * Write a method indexOfMax that has one parameter named values that is an integer array.
       This method returns the index position of the largest element in values.
       Then add code to the method testCountWordLengths to call indexOfMax to determine the most common word length in the file
       For example, calling indexOfMax after calling countWordLengths on the file smallHamlet.txt should return 3.
     */
    public int indexOfMax(int[] values) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i; 
            }
        }
        return index;
    }

}