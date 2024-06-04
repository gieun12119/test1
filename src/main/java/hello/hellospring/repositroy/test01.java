//package hello.hellospring.repositroy;
//
//import hello.hellospring.domain.Member;
//
//import javax.swing.text.html.Option;
//import java.util.*;
//
//public class test01 implements MemberRepository{
//
//  private Map<Long, Member> store = new HashMap<>();
//  private long sequence;
//
//  @Override
//  public Member save(Member member) {
//    member.setId(sequence++);
//    store.put(member.getId(), member);
//    return member;
//  }
//
//  @Override
//  public Optional<Member> findById(Long id) {
//    Member member = store.get(id);
//    return Optional.ofNullable(member);
////    return Optional.ofNullable(store.get(id));
////    결과가 없으면 null, null이 반환될 가능성이 있으면 Optional 사용
//  }
//
//  @Override
//  public Optional<Member> findByName(String name) {
//    return store.values().stream().filter(member -> member.getName().equals(name))
//        .findAny();
//  }
//
//  @Override
//  public List<Member> findAll() {
//    return new ArrayList<>(store.values());
//  }
//}
