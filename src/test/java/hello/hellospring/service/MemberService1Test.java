package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Member1;
import hello.hellospring.repositroy.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberService1Test {
  //서비스를 만들려면 저장소가 필요
  MemoryMemberRepository repository;
  MemberService1 memberService1;

  @BeforeEach
  public void beforeEach(){
    repository = new MemoryMemberRepository();
    memberService1 = new MemberService1(repository);
  }

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  void join() { //회원가입
    //given(어떤 걸 주어서)
    Member member = new Member();
    member.setName("hello");
    //when (join을 검증)
    Long saveId = memberService1.join(member);
    //중복 회원 검사 - save()
    //then (결과값)
    Member findMember = memberService1.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    memberService1.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService1.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 값이 존재하는 회원입니다.");

    }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}