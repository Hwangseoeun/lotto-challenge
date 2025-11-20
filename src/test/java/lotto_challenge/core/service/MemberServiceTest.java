package lotto_challenge.core.service;

import lotto_challenge.console.config.FakeAppConfig;
import lotto_challenge.core.repository.FakeMemberRepository;
import lotto_challenge.core.service.dto.SaveEmailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MemberServiceTest {

    private static MemberService memberService;
    private static FakeMemberRepository fakeMemberRepository;

    @BeforeEach
    void setUp() {
        FakeAppConfig config = new FakeAppConfig();
        fakeMemberRepository = config.createFakeMemberRepository();
        memberService = config.createMemberService(fakeMemberRepository);
    }

    private static MemberService createMemberService() {
        return new MemberService(fakeMemberRepository);
    }

    @DisplayName("생성자에 유효한 입력값(member repository)을 입력하면 MemberService 인스턴스가 생성된다.")
    @Test
    void givenMemberRepository_whenCreateMemberService_thenSuccess() {
        //When & Then
        assertThatCode(() -> createMemberService())
            .doesNotThrowAnyException();
    }

    @DisplayName("유효한 dto(email)을 입력하면 저장한 사용자의 ID를 반환한다.")
    @Test
    void givenEmail_whenSaveMember_thenReturnMemberId() {
        //Given
        final SaveEmailDto dto = new SaveEmailDto("email@gmail.com");

        //When
        final Long memberId = memberService.save(dto);

        //Then
        assertThat(memberId).isEqualTo(1L);
    }
}