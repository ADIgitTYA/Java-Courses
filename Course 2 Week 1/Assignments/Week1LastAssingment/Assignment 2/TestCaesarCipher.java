import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/*
 * Create the TestCaesarCipherTwo class with the following parts:

Include the methods halfOfString, countLetters, and maxIndex that you wrote in the previous lesson.
Write the void method simpleTests that has no parameters. This method should read in a file as a String, create a CaesarCipherTwo object
with keys 17 and 3, encrypt the String using the CaesarCipherTwo object, print the encrypted String, and decrypt the encrypted String
using the decrypt method.
Write the method breakCaesarCipher that has one String parameter named input. This method should figure out which keys were used 
to encrypt this message (in a similar manner as before), then create a CaesarCipherTwo object with that key and decrypt the message.
In the simpleTests method, add a call to breakCaesarCipher on the encrypted String to decrypt it automatically by determining the keys, 
and then print the decrypted String.
 */
public class TestCaesarCipher {
    private String halfOfString(String message , int start) {
        String result = new String();
        for (int i = start; i < message.length(); i += 2) {
            result = result + message.charAt(i);
        }
        return result;
    }
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }   
    private int maxIndex(int [] counters) {
        int maxcounter = 0;
        int maxposition = 0;
        for (int i = 0; i < counters.length; i++) {
            if (maxcounter < counters[i]) {
                maxposition = i;
                maxcounter = counters[i];
            }
        }
        return maxposition;
    }
    public void simpleTests() {
        //FileResource fr = new FileResource();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwo cc = new CaesarCipherTwo(14 , 24);
        //String encrypted = cc.encrypt(message);
        //System.out.println("encrypted : " + encrypted);
        System.out.println("dencrypted" + cc.decrypt(message));
        System.out.println( "keys + decryption : " + this.breakCaesarCipher(message));
    }
    public String breakCaesarCipher(String input) {
        String half0 = halfOfString(input , 0);
        String half1 = halfOfString(input , 1);
        int[] freq0 = countLetters(half0);
        int[] freq1 = countLetters(half1);
        int maxIndex0 = maxIndex(freq0);
        int maxIndex1 = maxIndex(freq1);
        int dkey1 = maxIndex0 - 4;
        int dkey2 = maxIndex1 - 4;
        if (dkey1 < 0) {
            dkey1 = 26 - (4 - maxIndex0);
        }
        if (dkey2 < 0) {
            dkey2 = 26 - (4 - maxIndex1);
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1 , dkey2);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
}