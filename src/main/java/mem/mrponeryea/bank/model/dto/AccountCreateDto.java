package mem.mrponeryea.bank.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class AccountCreateDto {

    @NotBlank(message = "Name must not be empty")
    String name;

    @Pattern(regexp = "\\d{4}", message = "PIN code must be 4 digits")
    String code;
}
