package mem.mrponeryea.bank.exception;

import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends BaseException{

    public InsufficientFundsException() {
        super(ErrorCode.NO_MONEY_ENOUGH, HttpStatus.BAD_REQUEST, "Insufficient funds in the account");
    }
}
