package lotto.domain;

import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final String ILLEGAL_LOTTO_BALL = "로또볼을 잘못 입력하였습니다. 재입력 해주세요.";
    private static final int LOTTO_TICKET_SIZE = 6;
    protected final List<LottoBall> lottoTicket;

    private LottoTicket(List<LottoBall> lottoTicket) {
        validateIllegalLottoNumberCount(lottoTicket);
        this.lottoTicket = Collections.unmodifiableList(lottoTicket);
    }


    public static LottoTicket of(List<LottoBall> lottoTicket) {
        return new LottoTicket(lottoTicket);
    }

    public static LottoTicket of(String inputLottoTicketNumber) {
        String[] ticketNumber = StringUtils.parseString(inputLottoTicketNumber);
        validateManualLottoTicket(ticketNumber);

        List<LottoBall> lottoTicket = Collections.unmodifiableList(
                Arrays.stream(ticketNumber)
                        .map(ball -> LottoBalls.findLottoBall(StringUtils.stringToInt(ball)))
                        .collect(Collectors.toList())
        );

        return of(lottoTicket);
    }

    private static void validateManualLottoTicket(String[] ticketNumber) {
        ValidationUtils.validateIntegerNumberFormat(ticketNumber);
        ValidationUtils.validatePositiveNumber(ticketNumber);
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }


    private static void validateIllegalLottoNumberCount(List<LottoBall> lottoTicket) {
        Set<LottoBall> compare = new HashSet<>(lottoTicket);

        if (compare.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(ILLEGAL_LOTTO_BALL);
        }
    }
}