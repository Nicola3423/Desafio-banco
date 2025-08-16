package desafio.backend.picpay.application.wallet;

import desafio.backend.picpay.application.wallet.dto.WalletResponse;
import desafio.backend.picpay.domain.wallet.Wallet;
import desafio.backend.picpay.domain.wallet.repository.WalletRepository;
import desafio.backend.picpay.application.wallet.dto.CreateWalletRequest;
import desafio.backend.picpay.domain.wallet.service.WalletValidatorExecutor;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletValidatorExecutor validatorExecutor;

    public WalletService(
            WalletRepository walletRepository,
            WalletValidatorExecutor validatorExecutor
    ) {
        this.walletRepository = walletRepository;
        this.validatorExecutor = validatorExecutor;
    }

    public WalletResponse createWallet(CreateWalletRequest request) {
        validatorExecutor.validate(request);

        Wallet savedWallet = walletRepository.save(request.toWallet());
        return convertToWalletResponse(savedWallet);
    }

    private WalletResponse convertToWalletResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getFullName(),
                wallet.getCpfCnpj(),
                wallet.getEmail(),
                wallet.getBalance(),
                wallet.getWalletType()
        );
    }
}