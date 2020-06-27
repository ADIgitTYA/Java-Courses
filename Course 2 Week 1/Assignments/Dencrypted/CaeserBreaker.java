import edu.duke.*;
import java.*;
/**
 * Write a description of TwoKeysDencrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaeserBreaker {
    /*
     * Complete the decryption method shown in the lesson by creating a CaesarBreaker class with the methods countLetters,
       maxIndex, and decrypt. Recall that the decrypt method creates a CaesarCipher object in order to use the encrypt method 
       you wrote for the last lesson. Make sure that your CaesarCipher class is in the same folder as CaesarBreaker!
       You may want to use the following code as part of your decrypt method.
       
     * CaesarCipher cc = new CaesarCipher();
     * String message = cc.encrypt(encrypted, 26 - key); 
     */
    public int[] countLetters(String message) {
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
    
    public int maxIndex(int [] counters) {
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (dkey < 0) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted , 26 - dkey);
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt("Aeroplane", 5);
        System.out.println(message);
        message = decrypt(message);
        System.out.println(message);
    }
    
    public String halfOfString(String message , int start) {
        String result = new String();
        for (int i = start; i < message.length(); i += 2) {
            result = result + message.charAt(i);
        }
        return result;
    }
    
    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        System.out.println(halfOfString(message , 1));
    }
    
    public int getKey(String s) {
        int[] freq = countLetters(s);
        int maxIndex = maxIndex(freq);
        int dkey = maxIndex - 4;
        if (dkey < 0) {
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }
    /*
     * Write the method decryptTwoKeys in the CaesarBreaker class that has one parameter, a String parameter named encrypted
      that represents a String that was encrypted with the two key algorithm discussed in the previous lesson. 
      This method attempts to determine the two keys used to encrypt the message, prints the two keys, and then returns
      the decrypted String with those two keys. More specifically, this method should:
      
      - Calculate a String of every other character starting with the first character of the encrypted String by calling 
        halfOfString.

      - Calculate a String of every other character starting with the second character of the encrypted String.

      - Then calculate the key used to encrypt each half String.

      - You should print the two keys found.

      - Calculate and return the decrypted String using the encryptTwoKeys method from your CaesarCipher class, 
        again making sure it is in the same folder as your CaesarBreaker class.
     */
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String messageF = halfOfString(encrypted, 0);
        String messageS = halfOfString(encrypted, 1);
        int key1 = getKey(messageF);
        int key2 = getKey(messageS);
        /*if (key1 < 0) {
            key1 = 26 - (4 - getKey(messageF));
        }
        if (key2 < 0) {
            key2 = 26 - (4 - getKey(messageS));
        }*/
        System.out.println(key1 + " " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    
    public void testdecryptTwoKeys() {
        //CaesarCipher cc = new CaesarCipher();
        //FileResource fr = new FileResource();
        //String encrypted = fr.asString();
        //System.out.println(decryptTwoKeys(encrypted));
        
        
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(decryptTwoKeys(encrypted));
        
    }
}
