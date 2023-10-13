package mem.mrponeryea.bank.exception;

import org.springframework.http.HttpStatus;

public class InvalidSaveException extends BaseException{

    public InvalidSaveException(String message) {
        super(ErrorCode.INVALID_SAVE, HttpStatus.BAD_REQUEST, message);
    }
}
