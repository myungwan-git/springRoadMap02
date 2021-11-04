package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();

    assertThat(prototypeBean1.getCount()).isSameAs(1);
    assertThat(prototypeBean2.getCount()).isSameAs(1);
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int logic1 = clientBean1.logic();
    assertThat(logic1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int logic2 = clientBean2.logic();
    assertThat(logic2).isEqualTo(2);
  }

  @Test
  void providerTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProviderBean.class, PrototypeBean.class);

    ProviderBean providerBean1 = ac.getBean(ProviderBean.class);
    int logic1 = providerBean1.logic();
    assertThat(logic1).isEqualTo(1);

    ProviderBean providerBean2 = ac.getBean(ProviderBean.class);
    int logic2 = providerBean2.logic();
    assertThat(logic2).isEqualTo(1);
  }


  static class ProviderBean {
    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }

  static class ClientBean {
    private PrototypeBean prototypeBean;

    @Autowired
    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      System.out.println(" @@@ " + count);

      return count;
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PostConstruct !!!!!!!!!!!!!!");
    }

    @PreDestroy
    public void close() {
      System.out.println("PreDestroy @@@@@@@@@@@@@@");

    }
  }
}
