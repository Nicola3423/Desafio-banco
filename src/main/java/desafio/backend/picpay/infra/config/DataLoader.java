package desafio.backend.picpay.infra.config;

import desafio.backend.picpay.domain.walletType.WalletType;
import desafio.backend.picpay.domain.walletType.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {


    private final WalletTypeRepository walletTypeRepository;


    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (WalletType.EnumWalletType enumType : WalletType.EnumWalletType.values()) {
            Long id = enumType.get().getId();
            String description = enumType.get().getDescription();
            if (!walletTypeRepository.existsById(id)) {
                walletTypeRepository.(new WalletType(id, description));
            }
        }
    }
}
