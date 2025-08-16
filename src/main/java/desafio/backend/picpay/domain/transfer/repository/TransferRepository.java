package desafio.backend.picpay.domain.transfer.repository;


import desafio.backend.picpay.domain.transfer.Transfer;
import desafio.backend.picpay.domain.wallet.Wallet;

public interface TransferRepository {
    Transfer save(Transfer transfer);
}