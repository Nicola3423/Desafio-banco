package desafio.backend.picpay.domain.wallet.repository;

import desafio.backend.picpay.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository {
    boolean existsByCpfCnpjOrEmail(String cpfCnpj, String email);
    Optional<Wallet> findById(Long id); // Adicionado
    Wallet save(Wallet wallet);
}
