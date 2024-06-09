package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JdbcTemplate 회원 레포지토리
 * 작성날짜 : 07.21(금)
 *
 * Jdbc api 의 반복적인 걸 제거.
 *
 * option +enter => implement method ( 메서드 자동 구현 기능 ?)
 *
 *
 */

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;


    /**
     * 스프링 빈에 등록된 생성자가 한개면 autowired 를 생략해도됨.
     * @param dataSource
     */
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
    /**
     * jdbc 랑도 비교 => 복잡한걸 2줄로 표현 가능하다
     * 왜 Template ? => 디자인 패턴에 Template 메서드방식으로 줄인거
     */
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",  memberRowMapper(), id);
        return result.stream().findAny();

    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?",  memberRowMapper(), name);
        return result.stream().findAny();
    }

    /**
     * 테이블 전체 정보를 조회할 수 있다.
     * List 로 반환하여 준다.
     *
     */
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member ",  memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        // 람다로 바꿀 수 있음. option + enter => replace lambda
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
