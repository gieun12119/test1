package hello.hellospring.repositroy;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Member1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepository1Test {

  MemoryMemberRepository1 repository1 = new MemoryMemberRepository1();

  @Test
  void save() {
    Member1 member1 = new Member1();
    member1.setName("spring");

    repository1.save(member1);
    Member1 result = repository1.findById(member1.getId()).get();
    Assertions.assertThat(member1).isEqualTo(result);

  }

  @Test
  void findById() {

  }

  @Test
  void findByName() {
    Member1 member1 = new Member1();
    member1.setName("spring1");
    repository1.save(member1);

    Member1 member2 = new Member1();
    member2.setName("spring2");
    repository1.save(member2);

    Member1 result = repository1.findByName("spring2").get();

    Assertions.assertThat(result).isEqualTo(member2);
  }

  @Test
  void findAll() {
  }
}