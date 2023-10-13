package mem.mrponeryea.bank.validation;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import mem.mrponeryea.bank.model.entity.AccountEntity;
import mem.mrponeryea.bank.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeValidation {
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    public boolean validatePin(Long accountNumber, Integer pin) {
        Optional<AccountEntity> account = accountRepository.findByNumber_Id(accountNumber);
        if (account.isPresent()) {
            AccountEntity accountEntity = account.get();
            return encoder.matches(String.valueOf(pin), accountEntity.getCode());
        }
        return false;
    }
}
