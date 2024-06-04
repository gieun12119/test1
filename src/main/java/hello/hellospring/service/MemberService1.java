package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositroy.MemberRepository;
import hello.hellospring.repositroy.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService1{
  //서비스를 만들려면 저장소가 필요함
  private final MemberRepository memberRepository;

  @Autowired //멤버 서비스는 멤버 리포지토리가 필요
  public MemberService1(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member){
    validateDuplicateMember(member);
    //회원 가입
    memberRepository.save(member);
    return member.getId();
    //같은 이름이 있으면 안된다.
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 값이 존재하는 회원입니다.");
        });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memmberId){
    return memberRepository.findById(memmberId);
  }

}