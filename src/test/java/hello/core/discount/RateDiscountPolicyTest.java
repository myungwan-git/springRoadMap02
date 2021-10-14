package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 무조건 10퍼센트가 할인 되어야 한다")
  void vip_o() {
    Member member = new Member(1L, "memberVIP", Grade.VIP);

    int discount = discountPolicy.discount(member, 10000);

    Assertions.assertThat(discount).isEqualTo(1000);
  }

  @Test
  @DisplayName("VIP가 아니면 0퍼센트가 할인 되어야 한다")
  void vip_x() {
    Member member = new Member(2L, "memberBasic", Grade.BASIC);

    int discount = discountPolicy.discount(member, 10000);

    Assertions.assertThat(discount).isEqualTo(0);
  }
}
