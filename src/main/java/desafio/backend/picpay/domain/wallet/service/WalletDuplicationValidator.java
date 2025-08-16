package desafio.backend.picpay.domain.wallet.service;

import desafio.backend.picpay.application.wallet.dto.CreateWalletRequest;
import desafio.backend.picpay.domain.wallet.exception.WalletDataAlreadyExistsException;
import desafio.backend.picpay.domain.wallet.repository.WalletRepository;
import org.springframework.stereotype.Component;

@Component
public class WalletDuplicationValidator implements WalletValidator {

    private final WalletRepository walletRepository;

    public WalletDuplicationValidator(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void validate(CreateWalletRequest request) {
        if (walletRepository.existsByCpfCnpjOrEmail(request.cpfCnpj(), request.email())) {
            throw new WalletDataAlreadyExistsException(
                    "Já existe uma carteira com este CPF/CNPJ ou e-mail"
            );
        }
    }
}
