package rs.ac.uns.ftn.clinic.security.crypto;

import java.security.InvalidKeyException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.stereotype.Component;

@Component
public class AesEncryptor extends Encryptor {

    protected AesEncryptor() throws Exception {
        super("AES");
    }

    @Override
    protected String encrypt(String content)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    @Override
    protected String decrypt(String content)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key);

        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }
}
