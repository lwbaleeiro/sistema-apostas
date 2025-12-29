package br.com.lwbaleeiro.gameodds.modules.users;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users_statistics")
@Getter
@Setter
public class UserStatistics {

    @Id
    private UUID userId;

    @Version
    private long version;

    private long totalGames;
    private long totalWins;
    private long totalLosses;
    private BigDecimal totalInvested;
    private BigDecimal totalWon;
    private BigDecimal totalLost;
    private BigDecimal totalProfit;
    private BigDecimal totalProfitPercentage;

    // public void registerWin(BigDecimal amount) {
    // this.totalWon = this.totalWon.add(amount);
    // this.totalProfit = this.totalProfit.add(amount);
    // this.totalProfitPercentage = this.totalProfit.divide(this.totalInvested, 2,
    // RoundingMode.HALF_UP)
    // .multiply(new BigDecimal(100));
    // this.totalInvested = this.totalInvested.add(amount);
    // this.totalLost = this.totalLost.subtract(amount);
    // }

    // public void registerLoss(BigDecimal amount) {
    // this.totalLost = this.totalLost.add(amount);
    // this.totalProfit = this.totalProfit.subtract(amount);
    // this.totalProfitPercentage = this.totalProfit.divide(this.totalInvested, 2,
    // RoundingMode.HALF_UP)
    // .multiply(new BigDecimal(100));
    // this.totalInvested = this.totalInvested.add(amount);
    // this.totalWon = this.totalWon.subtract(amount);
    // }
}
