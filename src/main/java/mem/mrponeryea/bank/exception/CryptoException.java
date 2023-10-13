package mem.mrponeryea.bank.exception;

import org.springframework.http.HttpStatus;

public class CryptoException extends BaseException{

    public CryptoException(String message) {
        super(ErrorCode.UNABLE_DECRYPT, HttpStatus.CONFLICT, message);
    }
}
