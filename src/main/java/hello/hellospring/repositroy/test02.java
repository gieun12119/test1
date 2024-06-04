package hello.hellospring.repositroy;

import hello.hellospring.domain.Member;

import java.util.*;

public class test02 implements MemberRepository {

  private Map<Long, Member> store = new HashMap<>();
  private long sequence = 0L;

  @Override
  public Member save(Member member) { //저장할 곳이 필요 두가지
    member.setId(sequence++);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream().filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }
}
