package desafio.backend.picpay.domain.transfer.service;

import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.domain.wallet.Wallet;

public interface TransferValidator {
    void validate(TransferRequest transferRequest, Wallet sender);
}
