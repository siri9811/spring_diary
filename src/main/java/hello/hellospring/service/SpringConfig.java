package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//자바 코드로 직접 빈 등록하기
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    //Dependeny Injection -> 필드 주입, setter 주입 ,*생성자 주입* 3가지 방법 존재
    //bean으로 등록되어있지 않으면 Autowired 작동 X

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
// helloController -> MemberService -> MemberRepository
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JdbcMemberRepository(dataSource);
    }
}

