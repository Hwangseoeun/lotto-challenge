package lotto_challenge.core.controller;

public interface MemberController {

    Long saveMember(final String email);
    Long getMember(final String email);
}
