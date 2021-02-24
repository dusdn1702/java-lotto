package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;

public class LottoGame {
    private final LottoTickets lottoTickets;
    private final Statistics statistics = new Statistics();

    public LottoGame(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Statistics calculateStatistics(WinnerTicket winnerTicket, BonusBall bonusBall) {
        statistics.calculateStatistics(lottoTickets.makeResult(winnerTicket, bonusBall));
        return statistics;
    }

    public Double calculateResult(Money money) {
        return new PrizeMoney(statistics).calculateProfit(money);
    }
}
