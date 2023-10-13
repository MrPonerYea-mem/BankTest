package mem.mrponeryea.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import mem.mrponeryea.bank.exception.InvalidSaveException;
import mem.mrponeryea.bank.listener.TransactionEvent;
import mem.mrponeryea.bank.mapper.AccountMapper;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import mem.mrponeryea.bank.repository.AccountRepository;
import mem.mrponeryea.bank.validation.CodeValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
public class AccountServiceIT {

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CodeValidation validation;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private AccountServiceImpl accountService;

    private static final String COMPANY_NAME = "some_name";
    private static final String NUMBER = "1234";

    @Test
    void create() {
        doThrow(InvalidSaveException.class)
            .when(accountRepository).findByName(COMPANY_NAME);
        assertThrows(InvalidSaveException.class, () -> accountService.create(new AccountCreateDto(COMPANY_NAME, NUMBER)));
    }
}
