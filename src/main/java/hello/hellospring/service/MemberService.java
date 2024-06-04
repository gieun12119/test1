package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repositroy.MemberRepository;
import hello.hellospring.repositroy.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
  private final MemberRepository memberRepository;
  //Member Service를 멤버 repository를 직넙 내가 new해서 생성하는게 아니라
  //외부에서 넣어주도록
  public  MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member){
    validateDuplicateMember(member); //중복 회원 검사
    //Optional을 바로 반환하는 건 좋지 않음
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
//  Optional<Member> result = memberRepository.findByName(member.getName());
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {  //값이 존재하면 "이미 존재하는 회원입니다"
          throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    //Optional<Member> Optional  Member 객체 존재
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }
}
