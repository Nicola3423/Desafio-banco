package desafio.backend.picpay.application.transfer.dto;

import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.domain.wallet.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferResponse(
        UUID id,
        BigDecimal value,
        Long senderId,
        Long receiverId
) {
}
