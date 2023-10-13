package mem.mrponeryea.bank.exception;

import org.springframework.http.HttpStatus;

public class InvalidCardNumberException extends BaseException{

    public InvalidCardNumberException() {
        super(ErrorCode.INVALID_CARD_NUMBER, HttpStatus.BAD_REQUEST, "Invalid card number");
    }
}
