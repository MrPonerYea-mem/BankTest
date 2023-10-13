package mem.mrponeryea.bank.repository;

import java.util.Optional;
import mem.mrponeryea.bank.model.entity.AccountEntity;
import mem.mrponeryea.bank.model.entity.NumberSequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByName(String name);

    Optional<AccountEntity> findByNumber_Id(Long numberId);
}
