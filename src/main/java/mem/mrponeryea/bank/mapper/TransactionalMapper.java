package mem.mrponeryea.bank.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import mem.mrponeryea.bank.listener.TransactionEvent;
import mem.mrponeryea.bank.model.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface TransactionalMapper {

    TransactionEntity fromDto(TransactionEvent event);
}
