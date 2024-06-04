package hello.hellospring.repositroy;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>(); //Long - key, member - value
  private static long sequence = 0L; // 0,1,2 키값을 생성해주는 애
  @Override
  public Member save(Member member) {
    //save 하기 위해선 저장할 곳이 필요 - Map
     member.setId(++sequence); // 아이디가 값을 하나 올려주고
     store.put(member.getId(), member); // store에 id값을 저장
     return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        // 멤버를 입력받아서 멤버의 이름과 파라미터에 입력된
        // 이름이 같으면 필터링 후 아무거나 하나 찾는다
        .findAny();
  }


  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}






