package br.com.lwbaleeiro.gameodds.modules.users;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "users_statistics")
public class UserStatistics {

    @Id
    private UUID userId;

    @Version
    private long version;

    private double totalInvested;
    private double totalWon;
    private double totalLost;
    private double totalProfit;
    private double totalProfitPercentage;

    public void registerWin(BigDecimal amount) {
        this.totalWon += amount.doubleValue();
        this.totalProfit += amount.doubleValue();
        this.totalProfitPercentage = this.totalProfit / this.totalInvested * 100;
        this.totalInvested += amount.doubleValue();
        this.totalLost -= amount.doubleValue();
    }

    public void registerLoss(BigDecimal amount) {
        this.totalLost += amount.doubleValue();
        this.totalProfit -= amount.doubleValue();
        this.totalProfitPercentage = this.totalProfit / this.totalInvested * 100;
        this.totalInvested += amount.doubleValue();
        this.totalWon -= amount.doubleValue();
    }
}
