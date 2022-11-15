// RSA program in JAVA using API
import java.security.*;
import javax.crypto.*;
import java.util.Base64;
 
public class myrsa 
{
 private static final int KEYSIZE = 512;
 public static void main(String[] args) 
 {
        KeyPair mykeyPair;
        try 
{
	     KeyPairGenerator mypair = KeyPairGenerator.getInstance("RSA");
 	     SecureRandom random = new SecureRandom();
       	     mypair.initialize(KEYSIZE, random);
                 mykeyPair = mypair.generateKeyPair();
 
           	     //mykeyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
 
                 final Cipher cipher = Cipher.getInstance("RSA");
                 final String plaintext = "Transfer one million doller";
                 System.out.println("Original Message ->"+plaintext +"\n");
          
           // ENCRYPT using the PUBLIC key
            cipher.init(Cipher.ENCRYPT_MODE, mykeyPair.getPublic());
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            String chipertext = new String(Base64.getEncoder().encode(encryptedBytes));
            System.out.println("Encrypted chipertext -> " + chipertext +"\n");
 
            // DECRYPT using the PRIVATE key
            cipher.init(Cipher.DECRYPT_MODE, mykeyPair.getPrivate());
            byte[] ciphertextBytes = Base64.getDecoder().decode(chipertext.getBytes());
            byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
            String decryptedString = new String(decryptedBytes);
            System.out.println("Decrypted plaintext -> " + decryptedString + "\n");
        } 
        catch (NoSuchAlgorithmException e) 
        {
            System.err.println("Algorithm not supported! " + e.getMessage() + "!");
        } 
        catch (NoSuchPaddingException | InvalidKeyException e) 
        {
            System.err.println("Cipher cannot be created!");
            e.printStackTrace();
        } 
        catch (BadPaddingException | IllegalBlockSizeException e) 
        {
            System.err.println("An error occurred during the encryption!");
            e.printStackTrace();
        }
    }//main method
}//class myrsa
