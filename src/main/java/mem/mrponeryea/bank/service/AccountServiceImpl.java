package mem.mrponeryea.bank.service;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mem.mrponeryea.bank.exception.InsufficientFundsException;
import mem.mrponeryea.bank.exception.InvalidCardNumberException;
import mem.mrponeryea.bank.exception.InvalidSaveException;
import mem.mrponeryea.bank.exception.PINCodeNotValid;
import mem.mrponeryea.bank.listener.TransactionEvent;
import mem.mrponeryea.bank.mapper.AccountMapper;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import mem.mrponeryea.bank.model.dto.AccountDto;
import mem.mrponeryea.bank.model.dto.OperationAccountDto;
import mem.mrponeryea.bank.model.dto.TransferDto;
import mem.mrponeryea.bank.model.entity.AccountEntity;
import mem.mrponeryea.bank.model.entity.TypeOperation;
import mem.mrponeryea.bank.repository.AccountRepository;
import mem.mrponeryea.bank.validation.CodeValidation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final CodeValidation validation;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AccountDto create(AccountCreateDto accountDto) {
        Optional<AccountEntity> account = accountRepository.findByName(accountDto.getName());
        if (account.isPresent()) {
            throw new InvalidSaveException("Card name is exist");
        }
        return Optional.of(accountDto)
            .map(accountMapper::createFromFto)
            .map(accountRepository::save)
            .map(accountMapper::toDto)
            .orElseThrow(() -> new InvalidSaveException("Something was wrong"));
    }

    @Override
    public Page<AccountDto> getAll(Pageable page) {
        return accountRepository.findAll(page).map(accountMapper::toDto);
    }

    @Override
    public AccountDto withdraw(Long accountNumber, OperationAccountDto accountDto) {
        AccountEntity accountEntity = getAccountEntityValid(accountNumber, Integer.valueOf(accountDto.getCode()));
        subtractAmount(accountDto.getAmount(), accountEntity);
        AccountDto dto = accountMapper.toDto(accountRepository.save(accountEntity));
        publishEvent(new TransactionEvent(this, TypeOperation.WITHDRAW, null, accountNumber, accountDto.getAmount()));
        return dto;
    }

    @Override
    public AccountDto deposit(Long accountNumber, OperationAccountDto accountDto) {
        AccountEntity accountEntity = getAccountEntityValid(accountNumber, Integer.valueOf(accountDto.getCode()));
        addAmount(accountDto.getAmount(), accountEntity);
        AccountDto dto = accountMapper.toDto(accountRepository.save(accountEntity));
        publishEvent(new TransactionEvent(this, TypeOperation.DEPOSIT, accountNumber, null, accountDto.getAmount()));
        return dto;
    }

    @Override
    public AccountDto transfer(TransferDto dto) {
        AccountEntity accountFrom = getAccountEntityValid(dto.getNumberFrom(), Integer.valueOf(dto.getCode()));
        AccountEntity accountTo = getAccountEntity(dto.getNumberTo());
        subtractAmount(dto.getAmount(), accountFrom);
        addAmount(dto.getAmount(), accountTo);
        publishEvent(new TransactionEvent(this, TypeOperation.TRANSFER, dto.getNumberTo(), dto.getNumberFrom(), dto.getAmount()));
        return accountMapper.toDto(accountFrom);
    }

    private void publishEvent(TransactionEvent event) {
        eventPublisher.publishEvent(event);
    }

    private static void addAmount(BigDecimal amount, AccountEntity accountEntity) {
        accountEntity.setBalance(accountEntity.getBalance().add(amount));
    }


    private AccountEntity getAccountEntityValid(Long number, Integer code) {
        AccountEntity account = getAccountEntity(number);
        if (validation.validatePin(number, code)) {
            return account;
        } else {
            throw new PINCodeNotValid();
        }

    }

    private AccountEntity getAccountEntity(Long number) {
        Optional<AccountEntity> account = accountRepository.findByNumber_Id(number);
        if (account.isPresent()) {
            return account.get();
        }
        throw new InvalidCardNumberException();
    }

    private void subtractAmount(BigDecimal amount, AccountEntity accountEntity) {
        BigDecimal divide = accountEntity.getBalance().subtract(amount);
        if (divide.floatValue() < 0) {
            throw new InsufficientFundsException();
        } else {
            accountEntity.setBalance(divide);
        }
    }
}
