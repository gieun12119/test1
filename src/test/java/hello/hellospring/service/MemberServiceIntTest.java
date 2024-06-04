package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositroy.MemberRepository;
import hello.hellospring.repositroy.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional //테스트가 끝나면 db롤백
class MemberServiceIntTest {

  @Autowired MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  @Test
  void 회원가입() {
    //given - 뭔가 주어지고
    Member member = new Member();
    member.setName("hello1");

    //when - 이걸 실행 했을때
    Long saveId = memberService.join(member);

    //then - 결과가 나와야한다
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복회원예외() {
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    memberService.join(member1);
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//    try{
//      memberService.join(member2);
//      fail("예외가 발생");
//    } catch (IllegalStateException e){
//      Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//    }
//  }
//
//    @Test
//    void findMembers () {
//
//    }
//
//    @Test
//    void findOne () {
//
//    }
  }
}