package mem.mrponeryea.bank.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import mem.mrponeryea.bank.model.dto.AccountDto;
import mem.mrponeryea.bank.model.dto.OperationAccountDto;
import mem.mrponeryea.bank.model.dto.TransferDto;
import mem.mrponeryea.bank.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Page<AccountDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(accountService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Validated AccountCreateDto accountDto) {
        return new ResponseEntity<>(accountService.create(accountDto), HttpStatus.CREATED);
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestBody @Validated OperationAccountDto dto,
                                              @PathVariable Long accountNumber) {
        return new ResponseEntity<>(accountService.deposit(accountNumber, dto), HttpStatus.OK);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestBody @Validated OperationAccountDto dto,
                                              @PathVariable Long accountNumber) {
        return new ResponseEntity<>(accountService.withdraw(accountNumber, dto), HttpStatus.OK);
    }
    @PostMapping("/transfer")
    public ResponseEntity<AccountDto> transferBetweenAccounts(@RequestBody TransferDto dto) {
        return new ResponseEntity<>(accountService.transfer(dto), HttpStatus.OK);
    }
}
