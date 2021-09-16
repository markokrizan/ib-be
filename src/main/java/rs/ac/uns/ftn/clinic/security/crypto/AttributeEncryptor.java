package rs.ac.uns.ftn.clinic.security.crypto;

import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

    private Encryptor encryptor;

    @Autowired
    public AttributeEncryptor(AesEncryptor aesEncryptor) throws Exception {
        encryptor = aesEncryptor;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return encryptor.encrypt(attribute);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return encryptor.decrypt(dbData);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
