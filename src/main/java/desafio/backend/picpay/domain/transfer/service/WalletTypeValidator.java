package desafio.backend.picpay.domain.transfer.service;

import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.domain.transfer.exception.TransferNotAllowedForWalletTypeException;
import desafio.backend.picpay.domain.wallet.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletTypeValidator implements TransferValidator {

    @Override
    public void validate(TransferRequest transferRequest, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }
    }
}

