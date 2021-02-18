package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.Tickets;
import lotto.lottoticket.WinnerTicket;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoCount lottoCount = generateCount(money);
        Tickets tickets = generateTickets(lottoCount);
        WinnerTicket winnerTicket = generateWinnerTicket();
        BonusBall bonusBall = generateBonusBall(winnerTicket);

        Statistics statistics = new Statistics(tickets.makeResult(winnerTicket, bonusBall));
        OutputView.noticeStatistics(statistics);
        PrizeMoney prizeMoney = new PrizeMoney(statistics);
        OutputView.showProfit(prizeMoney.calculateProfit(money));
    }

    private Money generateMoney() {
        try {
            OutputView.enterPurchaseMoney();
            return InputView.inputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateMoney();
        }
    }

    private LottoCount generateCount(Money money) {
        try {
            LottoCount lottoCount = new LottoCount(money);
            OutputView.noticeLottoCount(lottoCount);
            return lottoCount;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateCount(money);
        }
    }

    private Tickets generateTickets(LottoCount lottoCount) {
        Tickets tickets = new Tickets(lottoCount, new RandomNumbersGenerator());
        OutputView.showTickets(tickets);
        return tickets;
    }

    private WinnerTicket generateWinnerTicket() {
        try {
            OutputView.enterWinnerTicket();
            return InputView.inputWinnerTicket();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateWinnerTicket();
        }
    }

    private BonusBall generateBonusBall(WinnerTicket winnerTicket) {
        try {
            OutputView.enterBonusBall();
            return InputView.inputBonusBall(winnerTicket);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateBonusBall(winnerTicket);
        }
    }
}
