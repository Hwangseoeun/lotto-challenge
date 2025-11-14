package lotto_challenge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LottoViewController {

    @GetMapping("/lotto")
    public String lottoPage() {
        return "lotto/lotto";
    }
}
