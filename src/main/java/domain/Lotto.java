package domain;

import java.util.List;

public class Lotto {

	public static final int FIXED_LOTTO_SIZE = 6;
	private static final String DUPLICATION_NUMBERS_MESSAGE = "중복된 숫자를 입력할 수 없습니다";
	private static final String NOT_LOTTO_FIXED_SIZE_MESSAGE = "로또 번호는 6개의 숫자여야 합니다";

	private final List<LottoNumber> lotto;

	public Lotto(final List<LottoNumber> lotto) {
		checkLottoNumber(lotto);
		this.lotto = lotto;
	}

	private void checkLottoNumber(final List<LottoNumber> lotto) {
		checkLottoNumberSize(lotto);
		checkDuplicateLottoNumber(lotto);
	}

	private void checkLottoNumberSize(final List<LottoNumber> lotto) {
		if (lotto.size() != FIXED_LOTTO_SIZE) {
			throw new IllegalArgumentException(NOT_LOTTO_FIXED_SIZE_MESSAGE);
		}
	}

	private void checkDuplicateLottoNumber(final List<LottoNumber> lotto) {
		boolean duplicated = lotto.stream()
			.distinct()
			.count() != lotto.size();

		if (duplicated) {
			throw new IllegalArgumentException(DUPLICATION_NUMBERS_MESSAGE);
		}
	}

	public boolean isContain(final LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public LottoRank confirmWinningResult(final Lotto winningNumbers, final LottoNumber bonusLottoNumber) {
		int count = (int)lotto.stream()
			.filter(winningNumbers::isContain)
			.count();
		return LottoRank.findRank(count, lotto.contains(bonusLottoNumber));
	}

	public List<LottoNumber> getLotto() {
		return lotto;
	}
}
