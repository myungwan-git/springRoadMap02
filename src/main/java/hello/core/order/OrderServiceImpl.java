package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import jdk.nashorn.internal.runtime.JSErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  //private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final MemberRepository memberRepository;

  //FixDiscountPolicy에서 RateDiscountPolicy 로 변경해야 할때 현재 클래스(OrderServiceImpl)의 코드를 변경해야 한다.
  // 이는 DIP 규칙의 위반이다.
  //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy;


  //RequiredArgsConstructor 애노테이션으로 대체가능.
  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  //TEST용도. 실제 인터페이스에는 적용하지 ㅏㅇㄴㅎ을거임.
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
