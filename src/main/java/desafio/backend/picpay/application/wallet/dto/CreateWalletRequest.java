package desafio.backend.picpay.application.wallet.dto;

import desafio.backend.picpay.domain.wallet.Wallet;
import desafio.backend.picpay.domain.walletType.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletRequest(@NotBlank String fullName,
                                  @NotBlank String cpfCnpj,
                                  @NotBlank String email,
                                  @NotBlank String password,
                                  @NotNull WalletType.EnumWalletType walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
