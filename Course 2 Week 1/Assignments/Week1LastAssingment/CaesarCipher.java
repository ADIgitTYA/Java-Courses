import edu.duke.*;
import java.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/*
 * Specifically, you should do the following.

Create the CaesarCipher class with the following parts:

Private fields for the alphabet and shiftedAlphabet
Write a constructor CaesarCipher that has one int parameter key. This method should initialize all the private fields of the class.
Write an encrypt method that has one String parameter named input. This method returns a String that is the input encrypted using 
shiftedAlphabet.
Write a decrypt method that has one String parameter named input. This method returns a String that is the encrypted String decrypted 
using the key associated with this CaesarCipher object. One way to do this is to create another private field mainKey,
which is initialized to be the value of key. Then you can create a CaesarCipher object within decrypt:
CaesarCipher cc = new CaesarCipher(26 - mainKey); and call cc.encrypt(input).

Create the TestCaesarCipher class with the following parts:

Include the methods countLetters and maxIndex that you wrote in the previous lesson.
Write the void method simpleTests that has no parameters. This method should read in a file as a String, create a CaesarCipher object with key 18, encrypt the String read in using the CaesarCipher object, print the encrypted String, and decrypt the encrypted String using the decrypt method.
Write the method breakCaesarCipher that has one String parameter named input. This method should figure out which key was used to encrypt this message (in a similar manner as the previous lesson), then create a CaesarCipher object with that key and decrypt the message.
In the simpleTests method, add a call to breakCaesarCipher on the encrypted String to decrypt it automatically by determining the key, and print the decrypted String.

 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0 , key);
        this.mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(currChar));
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (Character.isUpperCase(currChar)) {
                    newChar = Character.toUpperCase(newChar);
                } else {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i , newChar);
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}