package mem.mrponeryea.bank.repository;

import mem.mrponeryea.bank.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<TransactionEntity, Long> {
}
