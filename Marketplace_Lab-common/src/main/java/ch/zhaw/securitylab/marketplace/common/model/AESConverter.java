package ch.zhaw.securitylab.marketplace.common.model;

import java.util.Base64;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ch.zhaw.securitylab.marketplace.common.service.AESCipherService;

@Converter
public class AESConverter implements AttributeConverter<String, String> {
    
    @Override
    public String convertToDatabaseColumn(String plainTextData) {
        byte[] encryptedData = ((new AESCipherService()).encrypt(plainTextData.getBytes()));
        return new String(Base64.getEncoder().encode(encryptedData));
    }

    @Override
    public String convertToEntityAttribute(String cipherTextData) {
        byte[] encryptedData = Base64.getDecoder().decode(cipherTextData);
        return new String((new AESCipherService()).decrypt(encryptedData));
    }
}
