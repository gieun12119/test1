package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  // memberService를 스프링이 스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결
  // @Controller를 스프링이 보는 순간 객체를 생성하여
  //auto-wired라고 되어 있으면 멤버 서비스를 스프링 컨테이너에서 관리하는
  //멤버서비스를 가져다가 스프링이 넣어준다 - (연결)
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new") //URL창 엔터
  public String createForm() {
    return "members/createMemberForm";
    // /members/new 들어가면 members/createMemberForm.html 렌더링
  }

  @PostMapping(value = "/members/new") // 데이터를 폼 같은데 넣어서 전달할 떄 POST, GET은 조회
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());
    //회원 폼에서 입력한 이름을 가져와서 해당 멤버 객체의 이름으로 설정하는 부분
    //회원 폼에서 입력한 이름을 가져오는 이유는 새로운 회원 객체를 생성할 때
    //그 이름을 사용자가 입력한 값으로 설정하기 위함
    //form.getName()은 회원 폼에서 사용자가 입력한 이름을 가져오는 메서드.
    // 설정된 이름은 회원 객체에 저장되어 회원 서비스를 통해 회원 가입 과정에 활용.


    memberService.join(member);

    return "redirect:/"; //사용자를 다른 url로 리다이렉트하는데 사용
    //회원 가입이 성공적으로 완료된 후에 사용자들 홈 페이지로 다시 보낸다
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members",members);
    //members라는 이름의 속성에 members 객체를 추가
    //모델에 추가된 데이터는 뷰에서 해당 이름을 사용하여 접근 가능
    //이를 통해 컨트롤러에서 처리된 데이터를 뷰로 전달하여 화면에 출력하거나
    //다양한 작업 가능
    //${members}는 컨트롤러에서 모델에 추가된 데이터
    return "members/memberList";
  }
}



