package hello.hellospring.controller;

import hello.hellospring.service.MemberService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController1 {
  private final MemberService1 memberService1;

  @Autowired //Dependyency Injection 의존관계 주입
  public MemberController1(MemberService1 memberService1) {
    this.memberService1 = memberService1;
  }

  //Spring이 관리를 하게 되면 Spring 컨테이너에 등록을 하고
  //Spring 컨테이너로 받아서 쓰도록 바꿔야 한다.
  //Spring 컨테이너에 등록하면 딱 하나만 등록
  //멤버 컨트롤러, 스프링 컨테이너가 뜰 때 생성을 하고 생성자를 호출
  //생성자에 AutoWired라고 있으면 멤버 서비스를 스프링이
  //스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결을 시켜준다
}
