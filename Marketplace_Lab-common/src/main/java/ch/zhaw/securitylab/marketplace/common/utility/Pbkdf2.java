package ch.zhaw.securitylab.marketplace.common.utility;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Pbkdf2 {
    private static final int ITERATIONS = 100000;
    private static final int SALT_SIZE_BYTES = 64;
    private static final int HASH_SIZE_BYTES = 32;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    
    private static byte[] createSalt() {
        byte[] salt = new byte[SALT_SIZE_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
    
    private static byte[] computeHash(String password, byte[] salt) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, HASH_SIZE_BYTES * 8);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(keySpec).getEncoded();
    }

    public static void main(String args[]) throws Exception {
        byte[] salt = createSalt();
        byte[] hash = computeHash(args[0], salt);
        System.out.println(ALGORITHM + ":" + ITERATIONS + ":" + 
                           Base64.getEncoder().encodeToString(salt) + ":" +
                           Base64.getEncoder().encodeToString(hash));
    }
}
