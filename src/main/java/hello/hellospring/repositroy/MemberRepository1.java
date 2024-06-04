package hello.hellospring.repositroy;

import hello.hellospring.domain.Member1;

import java.util.List;
import java.util.Optional;

public interface MemberRepository1 {
  Member1 save(Member1 member);
  Optional<Member1> findById(Long id);
  Optional<Member1> findByName(String name);
  List<Member1> findAll();

}
