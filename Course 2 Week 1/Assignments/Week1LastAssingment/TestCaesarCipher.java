import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/*
 * Create the TestCaesarCipher class with the following parts:

Include the methods countLetters and maxIndex that you wrote in the previous lesson.
Write the void method simpleTests that has no parameters. This method should read in a file as a String, create a CaesarCipher object 
with key 18, encrypt the String read in using the CaesarCipher object, print the encrypted String, and decrypt the encrypted String using 
the decrypt method.
Write the method breakCaesarCipher that has one String parameter named input. This method should figure out which key was used to encrypt
this message (in a similar manner as the previous lesson), then create a CaesarCipher object with that key and decrypt the message.
In the simpleTests method, add a call to breakCaesarCipher on the encrypted String to decrypt it automatically by determining the key, and 
print the decrypted String.
 */
public class TestCaesarCipher {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(message);
        System.out.println("encrypted : " + encrypted);
        System.out.println("dencrypted : " + cc.decrypt(encrypted));
        System.out.println("keys + dencryption " + this.breakCaesarCipher(message));
    }
    
    public String breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        int maxIndex = maxIndex(freq);
        int dkey = maxIndex - 4;
        
        if (dkey < 0) {
            dkey = 26 - (4 - maxIndex);
        }
        
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
}