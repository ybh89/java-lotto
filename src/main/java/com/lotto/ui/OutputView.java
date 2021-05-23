package com.lotto.ui;

import com.lotto.domain.Lotto;
import com.lotto.domain.LottoReward;

import java.util.List;
import java.util.Map;

import static com.lotto.ui.Message.*;

public class OutputView {

    static void out(String message) {
        System.out.println(message);
    }

    public static void requireLottoPrice() {
        out(REQUIRE_LOTTO_PRICE.message());
    }

    public static void confirmBuyCount(int buyCount) {
        out(buyCount + CONFIRM_BUY_COUNT.message());
    }

    public static void buyLottoList(List<Lotto> lottoList) {
        lottoList.forEach(lotto -> out(lotto.numbers().toString()));
    }

    public static void requireWinningNumbers() {
        out(REQUIRE_WINNING_NUMBERS.message());
    }

    public static void winningStatistics() {
        out(WINNING_STATISTICS.message());
        out("---------");
    }

    public static void winningStatisticsDetail(Map<LottoReward, Integer> winningLottoMap) {
        for (LottoReward reward : winningLottoMap.keySet()) {
            String message = getMessage(reward);
            out(String.format(WINNING_STATISTICS_DETAIL.message(),
                    message, reward.reward(), winningLottoMap.get(reward)));
        }
    }

    public static void investment(double investment) {
        out(String.format(INVESTMENT.message(), investment));
    }

    private static String getMessage(LottoReward reward) {
        if (reward == LottoReward.THREE) {
            return "3";
        } else if (reward == LottoReward.FOUR) {
            return "4";
        } else if (reward == LottoReward.FIVE) {
            return "5";
        } else if (reward == LottoReward.SIX) {
            return "6";
        }
        return "";
    }
}
