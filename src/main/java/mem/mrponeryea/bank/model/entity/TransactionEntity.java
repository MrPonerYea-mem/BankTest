package mem.mrponeryea.bank.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="from_account")
    private Long fromAccount;

    @Column(name ="to_account")
    private Long toAccount;

    @Column(name ="amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
}
