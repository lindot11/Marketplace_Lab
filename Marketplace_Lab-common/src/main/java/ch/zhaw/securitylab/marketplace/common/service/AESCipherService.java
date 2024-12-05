package ch.zhaw.securitylab.marketplace.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.crypto.spec.SecretKeySpec;

public class AESCipherService {

    private final static String CIPHER_ALGORITHM = "AES";
    private final static String CIPHER_ALGORITHM_FULL = "AES/GCM/NoPadding";
    private final static int BLOCKSIZE = 16;
    private final static int AUTH_TAG_LENGTH = 128;
    private static SecretKeySpec keySpec;

    // Static initializer to read key from file system and set keyspec
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().
                    getContextClassLoader().getResourceAsStream("db-key")));
            String hexKey = reader.readLine();
            byte[] byteKey = toByteArray(hexKey);
            keySpec = new SecretKeySpec(byteKey, CIPHER_ALGORITHM);
        } catch (IOException e) {
            // Do nothing
        }
    }
    
    /**
     * Encrypts (and integrity-protects) the plaintext with AES in GCM mode, 
     * selecting a random IV. The length of the Auth Tag is 128 bits.
     * 
     * @param plaintext The plaintext to encrypt
     * @return The IV and ciphertext (concatenated)
     */
    public byte[] encrypt(byte[] plaintext) {
        
        // Implement
        
        return null;
    }

   /**
     * Decrypts iv_ciphertext with AES in GCM mode.
     * 
     * @param iv_ciphertext The IV and ciphertext (concatenated)
     * @return The plaintext
     */
    public byte[] decrypt(byte[] iv_ciphertext) {
        
        // Implement
        
        return null;
    }
    
    private static byte[] toByteArray(String hexString) {
        int len = hexString.length();
        byte[] bytes = new byte[len/2];
        for (int i = 0; i < len; i += 2) {
            bytes[i/2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + 
                                  Character.digit(hexString.charAt(i+1), 16));
        }
        return bytes;
    }
}
