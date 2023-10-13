package mem.mrponeryea.bank.service;

import java.util.List;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import mem.mrponeryea.bank.model.dto.AccountDto;
import mem.mrponeryea.bank.model.dto.OperationAccountDto;
import mem.mrponeryea.bank.model.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountDto create(AccountCreateDto accountDto);

    Page<AccountDto> getAll(Pageable page);

    AccountDto deposit(Long accountNumber, OperationAccountDto accountDto);

    AccountDto withdraw(Long accountNumber, OperationAccountDto accountDto);

    AccountDto transfer(TransferDto dto);
}
