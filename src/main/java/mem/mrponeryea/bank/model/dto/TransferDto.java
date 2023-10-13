package mem.mrponeryea.bank.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferDto {
    @NotNull
    private Long numberTo;

    @NotNull
    private Long numberFrom;

    @NotNull
    private BigDecimal amount;

    @Pattern(regexp = "\\d{4}", message = "PIN code must be 4 digits")
    private String code;
}
