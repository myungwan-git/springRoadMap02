package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

  // 고정할인 정책 ( 무조건 1000원 )
  private int discountFixAmount = 1000;

  @Override
  public int discount(Member member, int price) {
    if(member.getGrade() == Grade.VIP) {
      return discountFixAmount;
    } else {
      return 0;
    }
  }
}
