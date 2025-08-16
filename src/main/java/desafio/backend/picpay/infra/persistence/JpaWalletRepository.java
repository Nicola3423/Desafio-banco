package desafio.backend.picpay.infra.persistence;

import desafio.backend.picpay.domain.wallet.Wallet;
import desafio.backend.picpay.domain.wallet.repository.WalletRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaWalletRepository implements WalletRepository {

    private final SpringWalletRepository delegate;

    public JpaWalletRepository(SpringWalletRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean existsByCpfCnpjOrEmail(String cpfCnpj, String email) {
        return delegate.existsByCpfCnpjOrEmail(cpfCnpj, email);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return delegate.findById(id);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return delegate.save(wallet);
    }

    public interface SpringWalletRepository extends org.springframework.data.jpa.repository.JpaRepository<Wallet, Long> {
        boolean existsByCpfCnpjOrEmail(String cpfCnpj, String email);


    }
}
