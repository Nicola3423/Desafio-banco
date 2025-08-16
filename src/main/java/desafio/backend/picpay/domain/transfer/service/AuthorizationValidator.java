package desafio.backend.picpay.domain.transfer.service;

import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.domain.client.service.AuthorizationService;
import desafio.backend.picpay.domain.transfer.exception.TransferNotAuthorizedException;
import desafio.backend.picpay.domain.wallet.Wallet;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationValidator implements TransferValidator {

    private final AuthorizationService authorizationService;

    public AuthorizationValidator(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void validate(TransferRequest transferRequest, Wallet sender) {
        if (!authorizationService.isAuthorized(transferRequest)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
