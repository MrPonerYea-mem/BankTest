package mem.mrponeryea.bank.exception;

import org.springframework.http.HttpStatus;

public class PINCodeNotValid extends BaseException {

    public PINCodeNotValid() {
        super(ErrorCode.PIN_CODE_NOT_VALID, HttpStatus.BAD_REQUEST, "PIN CODE NOT VALID");
    }
}
