package by.mybrik.repository;

import by.mybrik.domain.Goods;
import by.mybrik.domain.IndividualOrder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TesterJDBCTemplate {

  public static void main(String[] args) {

      AnnotationConfigApplicationContext annotationConfigApplicationContext =
              new AnnotationConfigApplicationContext("by.mybrik");


//      GoodsRepository productBean = annotationConfigApplicationContext.getBean(GoodsRepository.class);

//      Goods productForSaving = Goods.builder()
//              .orderCode("someOrderCode6")
//              .name("cap")
//              .photo("linkToPhoto")
//              .gender("Male")
//              .size("52")
//              .color("red")
//              .description("summer cap")
//              .isDeleted(false)
//              .price(15)
//              .quantity(15)
//              .category("caps")
//              .build();
//        System.out.println(productBean.save(productForSaving));
//    System.out.println(productBean.findAll());
//    System.out.println(productBean.findById(7l));

//      Goods productForUpdate = Goods.builder()
//              .id(7l)
//              .orderCode("someOrderCode7ForUpdate")
//              .name("cap")
//              .photo("linkToPhoto")
//              .gender("Male")
//              .size("52")
//              .color("red")
//              .description("summer cap")
//              .isDeleted(false)
//              .price(15)
//              .quantity(15)
//              .category("caps")
//              .build();
//
//    System.out.println(productBean.update(productForUpdate));
//
//      Goods productForDelete = Goods.builder()
//              .id(7l)
//              .orderCode("someOrderCode7ForUpdate")
//              .name("cap")
//              .photo("linkToPhoto")
//              .gender("Male")
//              .size("52")
//              .color("red")
//              .description("summer cap")
//              .isDeleted(false)
//              .price(15)
//              .quantity(15)
//              .category("caps")
//              .build();
//
//    System.out.println(productBean.delete(productForDelete));

//      GoodsRepository productBean = annotationConfigApplicationContext.getBean(GoodsRepository.class);
//      IndividualOrderRepository orderBean = annotationConfigApplicationContext.getBean(IndividualOrderRepository.class);
//      IndividualOrder order = IndividualOrder.builder()
//              .userId(2)
//              .textileId(1)
//              .productTypeId(1)
//              .priceId(1)
//              .quantity(1)
//              .totalprice(10)
//              .orderStatus("created")
//              .build();

    //        System.out.println(orderBean.save(order));
    //    System.out.println(orderBean.findById(1l));

//    System.out.println(orderBean.findAll());

//      IndividualOrder orderForUpdate = IndividualOrder.builder()
//              .id(3)
//              .userId(2)
//              .textileId(1)
//              .productTypeId(1)
//              .priceId(1)
//              .quantity(1)
//              .totalprice(100)
//              .orderStatus("done")
//              .build();

    //    System.out.println(orderBean.update(orderForUpdate));
//    System.out.println(orderBean.delete(orderForUpdate));

      UsersRepository userBean = annotationConfigApplicationContext.getBean(UsersRepository.class);

//    System.out.println(userBean.findById(2l));
//    System.out.println(userBean.findAll());
  }
}
