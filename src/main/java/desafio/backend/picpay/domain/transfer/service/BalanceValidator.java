package desafio.backend.picpay.domain.transfer.service;

import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.domain.transfer.exception.InsufficientBalanceException;
import desafio.backend.picpay.domain.wallet.Wallet;
import org.springframework.stereotype.Component;

@Component
public class BalanceValidator implements TransferValidator {

    @Override
    public void validate(TransferRequest transferRequest, Wallet sender) {
        if (!sender.isBalancerEqualOrGreatherThan(transferRequest.value())) {
            throw new InsufficientBalanceException();
        }
    }
}
