package lotto_challenge.core.controller;

import lotto_challenge.core.controller.dto.request.GenerateLottosRequestDto;
import lotto_challenge.core.controller.dto.request.LottoStatisticRequestDto;
import lotto_challenge.core.controller.dto.response.GenerateLottosResponseDto;
import lotto_challenge.core.controller.dto.response.LottoStatisticResponseDto;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.LottoService;
import lotto_challenge.core.service.LottoStatisticService;
import lotto_challenge.core.service.MemberService;
import lotto_challenge.core.service.dto.GenerateLottoDto;
import lotto_challenge.core.service.dto.GetLottoStatisticDto;
import lotto_challenge.core.service.dto.JudgeRankDto;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import lotto_challenge.core.service.dto.LottosDetailDto;
import lotto_challenge.core.service.dto.SaveEmailDto;
import lotto_challenge.core.service.dto.SaveLottoStatisticDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final MemberService memberService;
    private final LottoService lottoService;
    private final LottoStatisticService lottoStatisticService;

    public MainController(
        final MemberService memberService,
        final LottoService lottoService,
        final LottoStatisticService lottoStatisticService
    ) {
        this.memberService = memberService;
        this.lottoService = lottoService;
        this.lottoStatisticService = lottoStatisticService;
    }

    @PostMapping("/api/lotto/generate")
    public GenerateLottosResponseDto generateLottos(@RequestBody final GenerateLottosRequestDto request) {
        final SaveEmailDto saveEmailDto = new SaveEmailDto(request.email());
        final Long memberId = memberService.save(saveEmailDto);

        final GenerateLottoDto generateLottoDto = new GenerateLottoDto(request.purchasePrice());
        final LottosDetailDto lottosDetail = lottoService.generateLottos(generateLottoDto);

        final WinningRankCounter winningRankCounter = calculateWinningRank(lottosDetail.lottos());

        final ReturnRate returnRate = calculateReturnRate(winningRankCounter, lottosDetail.purchasePrice());

        final SaveLottoStatisticDto saveLottoStatisticDto = new SaveLottoStatisticDto(memberId, lottosDetail.purchasePrice(), returnRate);
        lottoStatisticService.saveLottoStatistic(saveLottoStatisticDto);

        return GenerateLottosResponseDto.from(lottosDetail, winningRankCounter, returnRate);
    }

    private WinningRankCounter calculateWinningRank(final Lottos lottos) {
        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        final JudgeRankDto judgeRankDto = new JudgeRankDto(lottos, winningRankCounter);

        lottoService.judgeRank(judgeRankDto);

        return winningRankCounter;
    }

    private ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice) {
        final int totalReturn = winningRankCounter.calculateReturn();
        return new ReturnRate(totalReturn, purchasePrice);
    }

    @PostMapping("/api/lotto/statistic")
    public LottoStatisticResponseDto getLottoStatistics(@RequestBody final LottoStatisticRequestDto request) {
        final GetLottoStatisticDto getLottoStatisticDto = new GetLottoStatisticDto(request.email());
        final List<LottoStatisticInfoDto> lottoStatisticInfo = lottoStatisticService.getLottoStatistics(getLottoStatisticDto);

        return new LottoStatisticResponseDto(lottoStatisticInfo);
    }
}
