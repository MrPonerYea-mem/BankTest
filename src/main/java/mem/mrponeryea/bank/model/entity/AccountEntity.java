package mem.mrponeryea.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mem.mrponeryea.bank.utils.EncodeDecodeStringConverter;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "account")
public class AccountEntity extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.valueOf(0);

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private NumberSequenceEntity number = new NumberSequenceEntity();

    @JsonIgnore
//    @Convert(converter = EncodeDecodeStringConverter.class)
    @Column(name = "code", nullable = false)
    private String code;
}
