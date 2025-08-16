package desafio.backend.picpay.domain.wallet.service;

import desafio.backend.picpay.application.wallet.dto.CreateWalletRequest;

public interface WalletValidator {
    void validate(CreateWalletRequest request);
}
