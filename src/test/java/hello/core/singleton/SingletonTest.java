package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수 DI 컨테이너 ( NOT Singleton )")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isNotSameAs(memberService2);


  }


  @Test
  @DisplayName("singleton 적용 확인")
  void SingletonServiceTest() {

    SingletonService singletonService1 = SingletonService.getSingleton();
    SingletonService singletonService2 = SingletonService.getSingleton();

    Assertions.assertThat(singletonService1).isSameAs(singletonService2);
  }


  @Test
  @DisplayName("스프링 컨테이너로 자동 구현되는 싱글톤 확인하기.")
  void springContainer() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isSameAs(memberService2);

  }

}
