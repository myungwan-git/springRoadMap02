package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingletonService {

  private static final SingletonService instance = new SingletonService();

  public static SingletonService getSingleton() {
    return instance;
  }

  // 외부에서 new 를 하지 못하게 기본생성자를 private으로 설정한다.
  private SingletonService() {
  }

  public void logic() {
    System.out.println("싱글톤 객체 호출");
  }

  @Test
  @DisplayName("singleton 적용 확인")
  void SingletonServiceTest() {

    SingletonService singletonService1 = SingletonService.getSingleton();
    SingletonService singletonService2 = SingletonService.getSingleton();

    Assertions.assertThat(singletonService1).isSameAs(singletonService2);


  }
}
