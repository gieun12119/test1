package hello.hellospring;

import hello.hellospring.repositroy.JdbcMemberRepository;
import hello.hellospring.repositroy.JdbcTemplateMemberRepository;
import hello.hellospring.repositroy.MemberRepository;
import hello.hellospring.repositroy.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration //스프링이 뜰 때 Configuratio을 읽고
public class SpringConfig {

  private DataSource dataSource;

  @Autowired public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }
  //서비스에 저장소 연결
  @Bean
  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//      return new JdbcMemberRepository(dataSource);
    return new JdbcTemplateMemberRepository(dataSource);
  }



}
