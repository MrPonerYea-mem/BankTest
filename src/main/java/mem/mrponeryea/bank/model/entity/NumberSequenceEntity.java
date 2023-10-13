package mem.mrponeryea.bank.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "number_Sequence")
public class NumberSequenceEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "card_number_seq"
    )
    @SequenceGenerator(
        name = "card_number_seq",
        initialValue = 1000_0000,
        allocationSize = 1
    )
    private Long id;
}
