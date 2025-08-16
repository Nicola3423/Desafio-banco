package desafio.backend.picpay.application.wallet.dto;

import desafio.backend.picpay.domain.wallet.Wallet;
import desafio.backend.picpay.domain.walletType.WalletType;

import java.math.BigDecimal;

public record WalletResponse(
        Long id,

        String fullName,
        String cpfCnpj,
        String email,
        BigDecimal balance,
        WalletType walletType
) {
    public static WalletResponse from(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getFullName(),
                wallet.getCpfCnpj(),
                wallet.getEmail(),
                wallet.getBalance(),
                wallet.getWalletType()
        );
    }
}
