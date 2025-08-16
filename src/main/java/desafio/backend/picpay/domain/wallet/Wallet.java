package desafio.backend.picpay.domain.wallet;

import desafio.backend.picpay.domain.walletType.WalletType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Email(message = "Formato de email inválido")
    @Column(name = "email", unique = true)
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Senha deve ter mínimo 8 caracteres, 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
    )
    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    public Wallet() {
    }

    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        if (!isValidCpfCnpj(cpfCnpj)) {
            throw new IllegalArgumentException("CPF/CNPJ inválido");
        }
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isTransferAllowedForWalletType() {
        return this.walletType.equals(WalletType.EnumWalletType.USER.get());
    }

    public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {
        return this.balance.doubleValue() >= value.doubleValue();
    }

    private boolean isValidCpfCnpj(String value) {
        if (value == null) return false;

        String cleanValue = value.replaceAll("[^0-9]", "");

        if (cleanValue.length() == 11) {
            return isValidCPF(cleanValue);
        } else if (cleanValue.length() == 14) {
            return isValidCNPJ(cleanValue);
        }
        return false;
    }

    private boolean isValidCPF(String cpf) {
        // Validação de dígitos repetidos (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int[] weights = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        // Calcula primeiro dígito
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights[i + 1];
        }
        int remainder = 11 - (sum % 11);
        int digit1 = (remainder >= 10) ? 0 : remainder;

        // Calcula segundo dígito
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights[i];
        }
        remainder = 11 - (sum % 11);
        int digit2 = (remainder >= 10) ? 0 : remainder;

        return digit1 == Character.getNumericValue(cpf.charAt(9)) &&
                digit2 == Character.getNumericValue(cpf.charAt(10));
    }

    private boolean isValidCNPJ(String cnpj) {
        // Validação de dígitos repetidos (ex: 11.111.111/1111-11)
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Calcula primeiro dígito
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights1[i];
        }
        int remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : 11 - remainder;

        // Calcula segundo dígito
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights2[i];
        }
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : 11 - remainder;

        return digit1 == Character.getNumericValue(cnpj.charAt(12)) &&
                digit2 == Character.getNumericValue(cnpj.charAt(13));
    }

    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }
}

