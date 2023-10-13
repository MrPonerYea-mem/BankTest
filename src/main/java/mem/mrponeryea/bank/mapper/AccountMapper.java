package mem.mrponeryea.bank.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import lombok.RequiredArgsConstructor;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import mem.mrponeryea.bank.model.dto.AccountDto;
import mem.mrponeryea.bank.model.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public abstract class AccountMapper {

    @Autowired
    protected PasswordEncoder encoder;

    @Mapping(target = "number", source = "accountEntity.number.id")
    public abstract AccountDto toDto(AccountEntity accountEntity);

    @Mapping(target = "code", expression = "java(encoder.encode(accountCreateDto.getCode()))")
    public abstract AccountEntity createFromFto(AccountCreateDto accountCreateDto);


}
