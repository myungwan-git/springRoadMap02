package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findByBeanName() {
    MemberService memberService = ac.getBean("memberService",MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

  }
  @Test
  @DisplayName("빈 이름없이 타입으로 조회")
  void findByBeanType() {
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

  }

  @Test
  @DisplayName("빈 이름으로 조회 실패 케이스")
  void findByBeanNameFail() {
    //NoSuchBeanDefinitionException 이 터지면(예외발생하면) 테스트 성공으로 나온다.
    org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
        () -> ac.getBean("XXX", MemberService.class));
  }

}
