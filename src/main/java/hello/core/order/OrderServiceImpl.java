package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();

  //FixDiscountPolicy에서 RateDiscountPolicy 로 변경해야 할때 현재 클래스(OrderServiceImpl)의 코드를 변경해야 한다.
  // 이는 DIP 규칙의 위반이다.
  //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private DiscountPolicy discountPolicy;


  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member,itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
