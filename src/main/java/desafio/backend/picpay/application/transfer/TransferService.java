package desafio.backend.picpay.application.transfer;

import desafio.backend.picpay.application.transfer.dto.TransferResponse;
import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.domain.transfer.repository.TransferRepository;
import desafio.backend.picpay.application.transfer.dto.TransferRequest;
import desafio.backend.picpay.domain.transfer.service.TransferValidator;
import desafio.backend.picpay.domain.wallet.exception.WalletNotFoundException;
import desafio.backend.picpay.domain.wallet.Wallet;
import desafio.backend.picpay.domain.wallet.repository.WalletRepository;
import desafio.backend.picpay.domain.client.service.NotificationService;
import desafio.backend.picpay.infra.persistence.JpaTransferRepository;
import desafio.backend.picpay.infra.persistence.JpaWalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;
    private final List<TransferValidator> validators;

    public TransferService(JpaTransferRepository transferRepository,
                           NotificationService notificationService,
                           JpaWalletRepository walletRepository,
                           List<TransferValidator> validators) {
        this.transferRepository =  transferRepository;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
        this.validators = validators;
    }

    @Transactional
    public TransferResponse transfer(TransferRequest transferRequest) {
        final Wallet sender = walletRepository.findById(transferRequest.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payer()));

        final Wallet receiver = walletRepository.findById(transferRequest.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payee()));

        validateTransfer(transferRequest, sender);

        sender.debit(transferRequest.value());
        receiver.credit(transferRequest.value());

        final Transfer transfer = new Transfer(sender, receiver, transferRequest.value());
        walletRepository.save(sender);
        walletRepository.save(receiver);

        final Transfer transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return mapToTransferResponse(transferResult);
    }

    private void validateTransfer(TransferRequest transferRequest, Wallet sender) {
        for (TransferValidator validator : validators) {
            validator.validate(transferRequest, sender);
        }
    }

    private TransferResponse mapToTransferResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getValue(),
                transfer.getSender().getId(),
                transfer.getReceiver().getId()
        );
    }

}
