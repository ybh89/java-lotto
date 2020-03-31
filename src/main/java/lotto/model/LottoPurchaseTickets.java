package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoPurchaseTickets {
    private List<LottoPurchaseTicket> lottoTickets;

    public LottoPurchaseTickets(final List<LottoPurchaseTicket> lottoTickets) {
        if (Objects.isNull(lottoTickets)) {
            throw new IllegalArgumentException("lotto tickets is null");
        }

        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public LottoResults checkAll(final WinningLottoTicket winningLottoTicket) {
        List<LottoResult> lottoResults = lottoTickets.stream()
                .map(ticket -> ticket.check(winningLottoTicket))
                .collect(Collectors.toList());

        return LottoResults.create(lottoResults);
    }

    public int size() {
        return lottoTickets.size();
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
                .map(LottoPurchaseTicket::toString)
                .collect(Collectors.joining("\n"));
    }
}