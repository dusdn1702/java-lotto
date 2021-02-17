package lotto.money;

import lotto.lottoticket.TicketValidation;

import java.util.Objects;

public class Money {
    private static final int MINIMUM_PRICE = 1000;
    private static final int SECOND_DECIMAL_POINT_MAKER = 100;

    private final int money;

    public Money(String money) {
        this.money = validate(money);
    }

    private int validate(String money) {
        int value = TicketValidation.validateNumber(money);
        checkMinimum(value);
        return value;
    }

    private void checkMinimum(int value) {
        if (value < MINIMUM_PRICE) {
            throw new IllegalArgumentException("[error] 1000원 이상의 금액이 필요합니다.");
        }
    }

    public int divideMoney(int unit) {
        return this.money / unit;
    }

    public String calculateProfit(int totalMoney) {
        double profit = Math.floor((double) totalMoney / money * SECOND_DECIMAL_POINT_MAKER) / SECOND_DECIMAL_POINT_MAKER;
        return Double.toString(profit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
