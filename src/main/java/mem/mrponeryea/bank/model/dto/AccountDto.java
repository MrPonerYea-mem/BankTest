package mem.mrponeryea.bank.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountDto {

    private String name;
    private BigDecimal balance;
    private Long number;
}
