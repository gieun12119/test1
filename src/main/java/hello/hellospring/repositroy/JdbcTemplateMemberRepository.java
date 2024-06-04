package hello.hellospring.repositroy;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

  private final JdbcTemplate jdbcTemplate; // Injection 받을 수 없음

  //생성자가 있으면 스프링 빈으로 등록이되고
  //스프링 빈으로 등록된걸 스프링컨테이너가 객체를 생성하고 관리하는데
  //여기서 생성자가 하나만 있으면 Autowired를 생략할 수 있다.
  @Autowired //생략 가능
  public JdbcTemplateMemberRepository(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }
  //스프링이 데이터 소스를 자동으로 Injection(주입) 해준다


  @Override
  public Member save(Member member) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", member.getName());
    Number key = jdbcInsert.executeAndReturnKey(new
        MapSqlParameterSource(parameters));
    member.setId(key.longValue());
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    List<Member> result = jdbcTemplate.query("select * from member where id = ?",
        memberRowMapper(), id);
      return result.stream().findAny();
    //조회하는 query , 결과가 나오는 것을 로우 맵퍼라는 것으로 맵핑
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result = jdbcTemplate.query("select * from member where name = ?",
        memberRowMapper(), name);
    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    return jdbcTemplate.query("select * from member", memberRowMapper());
  }

  private RowMapper<Member> memberRowMapper(){
    return (rs, rowNum) -> {

        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setName(rs.getString("name"));
        return member;
    };
  }
}
