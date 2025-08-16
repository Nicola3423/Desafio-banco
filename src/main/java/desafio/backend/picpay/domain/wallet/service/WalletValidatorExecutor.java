package desafio.backend.picpay.domain.wallet.service;

import desafio.backend.picpay.application.wallet.dto.CreateWalletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletValidatorExecutor {

    private final List<WalletValidator> validators;

    public WalletValidatorExecutor(List<WalletValidator> validators) {
        this.validators = validators;
    }

    public void validate(CreateWalletRequest request) {
        for (WalletValidator validator : validators) {
            validator.validate(request);
        }
    }
}
