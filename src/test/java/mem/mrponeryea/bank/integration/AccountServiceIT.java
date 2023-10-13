package mem.mrponeryea.bank.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mem.mrponeryea.bank.integration.annotation.IT;
import mem.mrponeryea.bank.model.dto.AccountDto;
import mem.mrponeryea.bank.model.dto.OperationAccountDto;
import mem.mrponeryea.bank.model.entity.AccountEntity;
import mem.mrponeryea.bank.repository.AccountRepository;
import mem.mrponeryea.bank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


@IT
@RequiredArgsConstructor
@Sql({
    "classpath:sql/data.sql"
})
public class AccountServiceIT {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    private static final Long ACCOUNT_NUMBER = 10000000L;
    private static final String CODE = "1231";

    @Test
    void deposit() {
        BigDecimal amount = BigDecimal.valueOf(100.00);
        OperationAccountDto operationAccountDto = new OperationAccountDto(CODE, amount);
        AccountDto deposit = accountService.deposit(ACCOUNT_NUMBER, operationAccountDto);
        Optional<AccountEntity> byNumberId = accountRepository.findByNumber_Id(ACCOUNT_NUMBER);
        assertTrue(byNumberId.isPresent());
        assertEquals(byNumberId.get().getNumber().getId(), ACCOUNT_NUMBER);
        assertEquals(byNumberId.get().getBalance().floatValue(), (amount.floatValue()));
    }
}
