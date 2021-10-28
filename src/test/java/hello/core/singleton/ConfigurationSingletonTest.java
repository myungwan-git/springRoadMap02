package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

   @Test
   void configurationTest() {
     ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
     OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

     //똑같은 객체이다. 신기하네. AppConfig에서 memberService/orderService에서 각각 MemberRepository를 생성했는데...
     System.out.println("orderService = " + orderService.getMemberRepository());
     System.out.println("memberService = " + memberService.getMemberRepository());

     //그렇다면 기본 memberRepository의 객체도 이와 같을까?
     MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
     System.out.println("memberRepository = " + memberRepository);
     //똑같다...

     //이게 무슨일인가 모두 똑같다. 실제로 AppConfig클래스에서 호출할때마다 프린트를 찍어보자.


     Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
     Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
   }

   @Test
   void configurationDeep() {
     ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
     AppConfig bean = ac.getBean(AppConfig.class);
     System.out.println("bean = " + bean.getClass());

   }

}
