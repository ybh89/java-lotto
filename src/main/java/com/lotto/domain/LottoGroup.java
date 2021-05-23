package com.lotto.domain;

import java.util.*;

public class LottoGroup {
    private List<Lotto> lottoList;

    public LottoGroup(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public int size() {
        return lottoList.size();
    }

    public List<Lotto> lottoList() {
        return lottoList;
    }

    public Map<LottoReward, Integer> winningLottoMap(Set<Integer> winningNumbers) {
        Map<LottoReward, Integer> winningLottoMap = new HashMap<>();
        for (Lotto lotto : lottoList) {
            LottoReward reward = lotto.reward(winningNumbers);
            mapOnlyWinningLotto(winningLottoMap, reward);
        }

        return winningLottoMap;
    }

    private void mapOnlyWinningLotto(Map<LottoReward, Integer> winningLottoMap, LottoReward reward) {
        if (reward != LottoReward.NOTHING) {
            winningLottoMap.put(reward, winningLottoMap.get(reward) != null ? winningLottoMap.get(reward) + 1 : 1);
        }
    }

    public int totalReward(Set<Integer> winningNumbers) {
        int totalReward = 0;
        Map<LottoReward, Integer> winningLottoMap = winningLottoMap(winningNumbers);
        for (LottoReward reward : winningLottoMap.keySet()) {
            totalReward += reward.totalReward(winningLottoMap.get(reward));
        }

        return totalReward;
    }

    public double yield(Set<Integer> winningNumbers) {
        int investment = lottoList.size() * Lotto.UNIT_PRICE;
        int totalReward = totalReward(winningNumbers);

        return totalReward / investment;
    }
}