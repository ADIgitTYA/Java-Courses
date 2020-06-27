import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                char ch = ' ';
                if (Character.isUpperCase(currChar)) {
                    ch = Character.toUpperCase(newChar);
                }
                else {
                    ch = Character.toLowerCase(newChar);
                }
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, ch);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 1;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    /*
     * Write the method encryptTwoKeys that has three parameters, a String named input, and two integers named key1 and key2.
       This method returns a String that has been encrypted using the following algorithm.
       Parameter key1 is used to encrypt every other character with the Caesar Cipher algorithm,
       starting with the first character, and key2 is used to encrypt every other character,
       starting with the second character.
       For example, the call encryptTwoKeys(“First Legion”, 23, 17) should return “Czojq Ivdzle”.
       Note the ‘F’ is encrypted with key 23, the first ‘i’ with 17, the ‘r’ with 23, and the ‘s’ with 17, etc.
       Be sure to test this method.
     */
    public String encryptTwoKeys(String input , int key1 , int key2) {
        String encryptkey1 = encrypt(input , key1);
        String encryptkey2 = encrypt(input , key2); 
        String encrypted = "";
        for (int character = 0; character < input.length(); character++) {
            if (character % 2 == 0) {
                encrypted += encryptkey1.charAt(character);
            } else {
                encrypted += encryptkey2.charAt(character);
            }
        }
        System.out.println(encrypted);
        return encrypted;
    }
}