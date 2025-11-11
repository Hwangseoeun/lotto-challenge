package lotto_challenge.web.controller;

import lotto_challenge.core.controller.LottoController;
import lotto_challenge.core.dto.CalculateRankRequestDto;
import lotto_challenge.core.dto.LottosInfoResponseDto;
import lotto_challenge.core.dto.PurchaseRequestDto;
import lotto_challenge.core.model.BonusNumber;
import lotto_challenge.core.model.Lotto;
import lotto_challenge.core.model.LottoQuantity;
import lotto_challenge.core.model.Lottos;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.model.WinningRankCounter;
import lotto_challenge.core.service.LottoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/lotto")
@RestController
public class WebLottoController implements LottoController {

    private final LottoService lottoService;

    public WebLottoController(final LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @Override
    @PostMapping("/generate")
    public LottosInfoResponseDto generateLottos(@RequestBody final PurchaseRequestDto dto) {
        final PurchasePrice price = new PurchasePrice(dto.purchasePrice());
        final LottoQuantity lottoQuantity = new LottoQuantity(price);
        final Lottos lottos = lottoService.generateLottos(lottoQuantity);

        return new LottosInfoResponseDto(price, lottoQuantity, lottos);
    }

    @Override
    @PostMapping("/rank")
    public WinningRankCounter calculateWinningRank(@RequestBody final CalculateRankRequestDto dto) {
        final Lotto winningLotto = new Lotto(dto.winningLottoNumbers());
        final BonusNumber bonus = new BonusNumber(dto.bonusNumber(), winningLotto);

        final WinningRankCounter winningRankCounter = new WinningRankCounter();
        lottoService.judgeRank(winningRankCounter, dto.lottos(), winningLotto, bonus);

        return winningRankCounter;
    }

    @Override
    public ReturnRate calculateReturnRate(final WinningRankCounter winningRankCounter, final PurchasePrice purchasePrice) {
        final int totalReturn = winningRankCounter.calculateReturn();
        return new ReturnRate(totalReturn, purchasePrice);
    }
}
