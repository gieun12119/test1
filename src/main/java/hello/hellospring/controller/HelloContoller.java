package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //해당 클래스를 웹 애플리케이션의 컨트롤러로 저장 HTTP 요청을 처리하고 적절한 응답을 반환
public class HelloContoller {

  @GetMapping("hello") //GET 요ㅕ청에 대한 핸들러 메서드를 매핑하는데 사용
  public String hello(Model model){
    model.addAttribute("name", "hello!!!");
    return "hello";
  }

  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model){
    //해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다. 주로 JSON 또는 XML 형식의 데이터를 반환합니다.
    model.addAttribute("name", name);
    return "hello-template";
  }

  @GetMapping("hello-string")
  public String helloString(@RequestParam("name") String name) {
    return "hello " + name;
  }

  @GetMapping("hello-api")
  public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;

  }

  static class Hello{
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
