package desafio.backend.picpay.infra.persistence;

import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.domain.transfer.repository.TransferRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransferRepository implements TransferRepository {

    private final SpringTransferRepository springRepo;

    public JpaTransferRepository(SpringTransferRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return springRepo.save(transfer);
    }


    public interface SpringTransferRepository extends JpaRepository<Transfer, UUID> {}
}
