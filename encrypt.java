package src.test.com.hays.java.shared;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class TestIncryption {
    private SecretKeySpec secretKey;
    public static void main(String [] args) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        String data ="{
  			"email_id":"abc@gmail.com",
			"user_id":"12469065",
			"session_id":"7e231809-8b08-4a26-8f98-555827d8b383",
			"expiry_time":"120820202332"
			}";
        TestIncryption hmacRowTest = new TestIncryption();

            hmacRowTest.encrypt(data,"SEFZU19QUk9KRUNUU19UUkFJTklOR19TRUNSRVQ=");

        }



    public String encrypt(String strToEncrypt, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
    setKey(secret);
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	}


    public void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("encrypt decrypt set key error"+e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

