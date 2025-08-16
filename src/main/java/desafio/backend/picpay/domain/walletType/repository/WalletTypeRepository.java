package desafio.backend.picpay.domain.walletType.repository;

import desafio.backend.picpay.domain.walletType.WalletType;

import java.util.Optional;


public interface WalletTypeRepository {
    WalletType save(WalletType walletType);
    Optional<WalletType> findById(Long id);
}
