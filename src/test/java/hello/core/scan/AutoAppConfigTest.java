package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

  @Test
  void basicScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService bean = ac.getBean(MemberService.class);
    assertThat(bean).isInstanceOf(MemberService.class);

    OrderServiceImpl bean1 = ac.getBean(OrderServiceImpl.class);
  }
}
