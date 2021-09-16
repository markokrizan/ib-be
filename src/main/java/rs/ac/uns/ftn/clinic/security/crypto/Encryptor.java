package rs.ac.uns.ftn.clinic.security.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public abstract class Encryptor {
    private static final String SECRET = "secret-key-12345";

    protected final Key key;
    protected final Cipher cipher;

    protected Encryptor(String algorithm) throws Exception {
        key = new SecretKeySpec(SECRET.getBytes(), algorithm);
        cipher = Cipher.getInstance(algorithm);
    }

    protected abstract String encrypt(String content) throws Exception;

    protected abstract String decrypt(String content) throws Exception;
}
