package desafio.backend.picpay.infra.config;

import desafio.backend.picpay.domain.transfer.repository.TransferRepository;
import desafio.backend.picpay.domain.wallet.repository.WalletRepository;
import desafio.backend.picpay.domain.walletType.repository.WalletTypeRepository;
import desafio.backend.picpay.infra.persistence.JpaTransferRepository;
import desafio.backend.picpay.infra.persistence.JpaWalletRepository;
import desafio.backend.picpay.infra.persistence.JpaWalletTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class PersistenceConfig {
    @Bean
    public TransferRepository transferRepository(TransferRepository springRepo) {
        return new JpaTransferRepository((JpaTransferRepository.SpringTransferRepository) springRepo);
    }

    @Bean
    public WalletRepository walletRepository(JpaWalletRepository.SpringWalletRepository springRepo) {
        return new JpaWalletRepository(springRepo);
    }

    @Bean
    public WalletTypeRepository walletTypeRepository(JpaWalletTypeRepository.SpringWalletTypeRepository walletTypeRepo) {
        return new JpaWalletTypeRepository(walletTypeRepo);
    }
}
