package desafio.backend.picpay.infra.persistence;

import desafio.backend.picpay.domain.walletType.WalletType;
import desafio.backend.picpay.domain.walletType.repository.WalletTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaWalletTypeRepository implements WalletTypeRepository {

    private final SpringWalletTypeRepository delegate;

    public JpaWalletTypeRepository(SpringWalletTypeRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public WalletType save(WalletType walletType) {
        return null;
    }

    @Override
    public Optional<WalletType> findById(Long id) {
        return delegate.findById(id);
    }

    public interface SpringWalletTypeRepository extends org.springframework.data.jpa.repository.JpaRepository<WalletType, Long> {
    }
}
