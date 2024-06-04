package hello.hellospring.repositroy;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class test02Test {

  MemoryMemberRepository memberRepository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }
  @Test
  void save() {
    Member member = new Member();
    member.setName("spring");

    memberRepository.save(member);
    Member result = memberRepository.findById(member.getId()).get();
    Assertions.assertThat(member).isEqualTo(result);
  }

  @Test
  void findById() {

  }

  @Test
  void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    memberRepository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    memberRepository.save(member2);

    Member result = memberRepository.findByName("spring1").get();
    Assertions.assertThat(result).isEqualTo(member1);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    memberRepository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    memberRepository.save(member2);

    List<Member> all = memberRepository.findAll();

    Assertions.assertThat(all.size()).isEqualTo(2);

  }
}