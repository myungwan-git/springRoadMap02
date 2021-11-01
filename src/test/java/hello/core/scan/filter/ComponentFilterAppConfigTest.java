package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;


public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanA bean = ac.getBean(BeanA.class);
    Assertions.assertThat(bean).isNotNull();

    org.junit.jupiter.api.Assertions.assertThrows(
        NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class)
    );
  }


  @Configuration
  @ComponentScan(
      includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  ) // 내가 만든 My~Component 애노테이션으로 컴포넌트스캔을 인클루드 및 익스클루드 한다.
  static class ComponentFilterAppConfig {

  }
}


