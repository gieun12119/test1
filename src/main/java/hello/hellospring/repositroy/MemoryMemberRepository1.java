package hello.hellospring.repositroy;

import hello.hellospring.domain.Member1;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository1 implements MemberRepository1 {
  //데이터:회원ID, 이름
  //기능:회원 등록, 조회

  private static Map<Long, Member1> store1 = new HashMap<>();
  // key - ID, Value = name
  private static long sequence = 0L;

  @Override
  public Member1 save(Member1 member) {
    member.setId(++sequence);
    store1.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member1> findById(Long id) {
    return Optional.ofNullable(store1.get(id));
  }

  @Override
  public Optional<Member1> findByName(String name) {
    return store1.values().stream()
        .filter(m -> m.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member1> findAll() {
    return new ArrayList<>(store1.values());
  }
}
