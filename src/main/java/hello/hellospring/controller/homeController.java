package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

  @GetMapping("/") // HTTP GET 요청이 "/" 경로로 들어왔을 때 메서드 실행
  public String home() {
    return "home";
//    클라이언트에게 보내는 응답의 내용
//    반환값으로 문자열을 반환하는 경우, 스프링은 이를 뷰 이름으로 해석하고
//    해당 뷰를 찾아서 렌더링.
  }
}
