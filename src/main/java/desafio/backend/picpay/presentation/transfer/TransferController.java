package desafio.backend.picpay.presentation.transfer;

import desafio.backend.picpay.application.transfer.dto.TransferResponse;
import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.application.transfer.TransferService;
import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> createTransfer(
            @RequestBody @Valid TransferRequest request
    ) {
        TransferResponse response = transferService.transfer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
