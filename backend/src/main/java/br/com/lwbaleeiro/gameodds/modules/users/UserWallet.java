package br.com.lwbaleeiro.gameodds.modules.users;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "users_wallet")
public class UserWallet {

    @Id
    private UUID userId;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal currency;

    @Version
    private long version;
}
