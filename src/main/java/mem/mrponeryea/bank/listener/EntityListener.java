package mem.mrponeryea.bank.listener;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mem.mrponeryea.bank.mapper.TransactionalMapper;
import mem.mrponeryea.bank.repository.TransactionalRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class EntityListener {

    private final TransactionalMapper mapper;
    private final TransactionalRepository repository;

    @EventListener
    public void transactionOperation(TransactionEvent transactionEvent) {
        repository.save(mapper.fromDto(transactionEvent));
    }
}
