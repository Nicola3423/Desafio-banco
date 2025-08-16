package desafio.backend.picpay.domain.client.service;

import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.infra.client.Authorization.AuthorizationClient;
import desafio.backend.picpay.infra.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorization;


    public AuthorizationService(AuthorizationClient authorization) {
        this.authorization = authorization;
    }

    public boolean isAuthorized(TransferRequest transferRequest) {

        var resp = authorization.isAuthorizd();

        if(resp.getStatusCode().isError()){
            throw new PicPayException();
        }
        return resp.getBody().authorized();
    }
}
