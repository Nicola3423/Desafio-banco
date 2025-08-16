package desafio.backend.picpay.application.transfer.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(@DecimalMin("0.01") @NotNull BigDecimal value,
                              @NotNull Long payer,
                              @NotNull Long payee) {
}
