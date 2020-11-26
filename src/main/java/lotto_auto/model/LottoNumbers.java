package lotto_auto.model;

import lotto_auto.ErrorMessage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbers {

    private static final Integer LOTTO_NUMBER_COUNT = 6;
    private static List<LottoNumber> lottoNumberList =
            IntStream
                    .range(1, 45)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());

    private List<LottoNumber> lottoNumbers;

    public static LottoNumbers generate() {
        Collections.shuffle(lottoNumberList);
        List<LottoNumber> list = new ArrayList<>(lottoNumberList.subList(0, 6));
        Collections.sort(list);
        return new LottoNumbers(list);
    }

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        throwIfNull(lottoNumbers);
        throwIfInValidLottoNumberCount(lottoNumbers);
        throwIfDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> export() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    public int computeMatchCount(LottoNumbers lottoNumbers) {
        return (int) lottoNumbers.export()
                .stream()
                .filter(this::contains)
                .count();
    }

    private void throwIfDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        if (numbers.size() != set.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER);
        }
    }

    private void throwIfNull(List<LottoNumber> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NULL_LOTTO_NUMBER);
        }
    }

    private void throwIfInValidLottoNumberCount(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT);
        }
    }

}