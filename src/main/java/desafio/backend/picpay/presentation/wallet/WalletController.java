package desafio.backend.picpay.presentation.wallet;

import desafio.backend.picpay.application.wallet.WalletService;
import desafio.backend.picpay.application.wallet.dto.WalletResponse;
import desafio.backend.picpay.application.wallet.dto.CreateWalletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody @Valid CreateWalletRequest walletDto) {
        var wallet = walletService.createWallet(walletDto);
        return ResponseEntity.ok(wallet);
    }
}
