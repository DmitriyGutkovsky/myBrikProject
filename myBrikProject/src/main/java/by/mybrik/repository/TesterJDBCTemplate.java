package by.mybrik.repository;

import by.mybrik.domain.Goods;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TesterJDBCTemplate {

  public static void main(String[] args) {

      AnnotationConfigApplicationContext annotationConfigApplicationContext =
              new AnnotationConfigApplicationContext("by.mybrik");


      GoodsRepository productBean = annotationConfigApplicationContext.getBean(GoodsRepository.class);

      Goods productForSaving = Goods.builder()
              .orderCode("someOrderCode6")
              .name("cap")
              .photo("linkToPhoto")
              .gender("Male")
              .size("52")
              .color("red")
              .description("summer cap")
              .isDeleted(false)
              .price(15)
              .quantity(15)
              .category("caps")
              .build();

    //        System.out.println(productBean.save(productForSaving));
    //    System.out.println(productBean.findAll());
    System.out.println(productBean.findById(7l));
  }
}
