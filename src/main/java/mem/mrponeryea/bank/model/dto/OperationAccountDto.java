package mem.mrponeryea.bank.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationAccountDto {
    @Pattern(regexp = "\\d{4}", message = "PIN code must be 4 digits")
    private String code;
    @NotNull(message = "Amount should not be null")
    private BigDecimal amount;
}
