package mem.mrponeryea.bank.utils;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import mem.mrponeryea.bank.exception.CryptoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

public class EncodeDecodeStringConverter implements AttributeConverter<String, String> {

    @Value("${crypto.algorithm}")
    private String ALGORITHM;

    @Value("${crypto.key}")
    private String SECRET_KEY;

    private SecretKey key;

    @PostConstruct
    public void generateSecretKey() {
        key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
    }

    @Override
    public String convertToDatabaseColumn(String value) {
        try {
            if (StringUtils.isEmpty(value)) {
                return value;
            }
            Cipher encryptCipher = Cipher.getInstance(ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(value.getBytes(StandardCharsets.UTF_8)));
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoException(e.getMessage());
        }
    }

    @Override
    public String convertToEntityAttribute(String encryptedValue) {
        try {
            if (StringUtils.isEmpty(encryptedValue)) {
                return encryptedValue;
            }
            Cipher decryptCipher = Cipher.getInstance(ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(
                encryptedValue.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoException(e.getMessage());
        }
    }
}
