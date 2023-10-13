package mem.mrponeryea.bank.listener;

import java.math.BigDecimal;
import java.util.EventObject;
import lombok.Getter;
import mem.mrponeryea.bank.model.entity.TypeOperation;

@Getter
public class TransactionEvent extends EventObject {

    private final TypeOperation typeOperation;
    private final Long toAccount;
    private final Long fromAccount;
    private final BigDecimal amount;


    /**
     * Constructs a prototypical Event.
     * @param source        the object on which the Event initially occurred
     * @param typeOperation
     * @param toAccount
     * @param fromAccount
     * @param amount
     * @throws IllegalArgumentException if source is null
     */
    public TransactionEvent(Object source, TypeOperation typeOperation, Long toAccount, Long fromAccount,
                            BigDecimal amount) {
        super(source);
        this.typeOperation = typeOperation;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
    }
}
