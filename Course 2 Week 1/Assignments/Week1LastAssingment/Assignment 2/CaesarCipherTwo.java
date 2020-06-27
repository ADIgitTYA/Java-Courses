import edu.duke.*;
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/*
 * Specifically, you should do the following.

Create the CaesarCipherTwo class with the following parts:

Include private fields for the alphabet, shiftedAlphabet1, and shiftedAlphabet2.
Write a constructor CaesarCipherTwo that has two int parameters key1 and key2. This method should initialize all the private fields.
Write an encrypt method that has one String parameter named input. This method returns a String that is the input encrypted using the
two shifted alphabets.
Write a decrypt method that has one String parameter named input. This method returns a String that is the encrypted String decrypted
using the key1 and key2 associated with this CaesarCipherTwo object. You might want to add more private fields to the class.
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1 , int key2) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0 , key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0 , key2);
        this.mainKey1 = key1;
        this.mainKey2 = key2;
    }   
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0 && alphabet.indexOf(Character.toUpperCase(currChar)) != -1) {
                int index = alphabet.indexOf(Character.toUpperCase(currChar));
                char newChar = shiftedAlphabet1.charAt(index);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i , newChar);
            } else if (alphabet.indexOf(Character.toUpperCase(currChar)) != -1) {
                int index = alphabet.indexOf(Character.toUpperCase(currChar));
                char newChar = shiftedAlphabet1.charAt(index);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i , newChar);
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1 , 26 - mainKey2);
        return cc.encrypt(input);
    }
}