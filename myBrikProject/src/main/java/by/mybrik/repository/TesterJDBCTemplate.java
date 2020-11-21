package by.mybrik.repository;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.domain.ProductType;
import by.mybrik.domain.StandardOrder;
import by.mybrik.domain.Textile;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

public class TesterJDBCTemplate {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext("by.mybrik");

    //      GoodsRepository productBean =
    // annotationConfigApplicationContext.getBean(GoodsRepository.class);

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

    //      GoodsRepository productBean =
    // annotationConfigApplicationContext.getBean(GoodsRepository.class);
    //      IndividualOrderRepository orderBean =
    // annotationConfigApplicationContext.getBean(IndividualOrderRepository.class);
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

    //      Users userForDelete = Users.builder()
    //              .id(7l)
    //              .name("save")
    //              .surName("save")
    //              .login("save")
    //              .password("save")
    //              .email("save@mail.ru")
    //              .gender(Gender.FEMALE)
    //              .phone(356985)
    //              .address("save")
    //              .isDeleted(false)
    //              .build();
    //
    //      System.out.println(userBean.delete(userForDelete));

    //      Users userForSaving = Users.builder()
    //              .name("save778")
    //              .surName("save7778")
    //              .login("save7778")
    //              .password("save778")
    //              .email("save7778@mail.ru")
    //              .gender(Gender.FEMALE)
    //              .phone(356985778)
    //              .address("save7778")
    //              .isDeleted(false)
    //              .build();
    //
    //
    //    System.out.println(userBean.save(userForSaving));
    //      Users userForUpdate = Users.builder()
    //              .id(10l)
    //              .name("save8899")
    //              .surName("save")
    //              .login("save809")
    //              .password("save809")
    //              .email("sav809@mail.ru")
    //              .gender(Gender.FEMALE)
    //              .phone(3569890)
    //              .changed(new Timestamp(new Date().getTime()))
    //              .address("sav809")
    //              .isDeleted(false)
    //              .build();
    //
    //
    //    System.out.println(userBean.update(userForUpdate));

    StandardOrderRepository standardOrderBean =
        annotationConfigApplicationContext.getBean(StandardOrderRepository.class);

    //      StandardOrder standardOrder = StandardOrder.builder()
    //              .goodId(6)
    //              .userId(10)
    //              .quantity(10)
    //              .totalPrice(25)
    //              .orderStatus("in progress")
    //              .build();

    //    System.out.println(standardOrderBean.save(standardOrder));
    //    System.out.println(standardOrderBean.findAll());
    //    System.out.println(standardOrderBean.findById(1l));

    //      StandardOrder standardOrderForUpdate = StandardOrder.builder()
    //              .id(2)
    //              .goodId(6)
    //              .userId(10)
    //              .quantity(100)
    //              .totalPrice(25)
    //              .orderStatus("in progress")
    //              .build();
    //
    //    System.out.println(standardOrderBean.update(standardOrderForUpdate));
    //      StandardOrder standardOrderForDelete = StandardOrder.builder()
    //              .id(2)
    //              .goodId(6)
    //              .userId(10)
    //              .quantity(100)
    //              .totalPrice(25)
    //              .orderStatus("in progress")
    //              .build();
    //
    //    System.out.println(standardOrderBean.delete(standardOrderForDelete));

    PriceForIndividualOrderRepository priceBean =
        annotationConfigApplicationContext.getBean(PriceForIndividualOrderRepository.class);

    //    System.out.println(priceBean.findById(1l));
    //    System.out.println(priceBean.findAll());
    //      PriceForIndividualOrder priceSave = PriceForIndividualOrder.builder()
    //              .productType("cap")
    //              .price(55)
    //              .isDeleted(false)
    //              .build();

    //    System.out.println(priceBean.save(priceSave));

    //      PriceForIndividualOrder priceUpdate = PriceForIndividualOrder.builder()
    //              .id(2l)
    //              .productType("Some cap")
    //              .price(65)
    //              .changed(new Timestamp(new Date().getTime()))
    //              .isDeleted(false)
    //              .build();
    //
    //    System.out.println(priceBean.update(priceUpdate));

    //            PriceForIndividualOrder priceDelete = PriceForIndividualOrder.builder()
    //              .id(2l)
    //              .productType("Some cap")
    //              .price(65)
    //              .changed(new Timestamp(new Date().getTime()))
    //              .isDeleted(false)
    //              .build();
    //
    //    System.out.println(priceBean.delete(priceDelete));
    ProductTypeRepository productTypeBean =
        annotationConfigApplicationContext.getBean(ProductTypeRepository.class);

    //    System.out.println(productTypeBean.findAll());
    //    System.out.println(productTypeBean.findById(1l));
    //      ProductType productTypeForDelete = ProductType.builder()
    //              .id(3)
    //              .productType("test2")
    //              .photo("test")
    //              .isDeleted(false)
    //              .build();
    //      System.out.println(productTypeBean.delete(productTypeForDelete));

    //      ProductType productTypeForSave = ProductType.builder()
    //              .productType("test2")
    //              .photo("test")
    //              .isDeleted(false)
    //              .build();
    //
    //    System.out.println(productTypeBean.save(productTypeForSave));

    //      ProductType productTypeForUpdate = ProductType.builder()
    //              .id(4l)
    //              .productType("test2ForUpdate")
    //              .photo("test")
    //              .isDeleted(false)
    //              .changed(new Timestamp(new Date().getTime()))
    //              .build();
    //
    //      System.out.println(productTypeBean.update(productTypeForUpdate));

    TextileRepository textileBean =
        annotationConfigApplicationContext.getBean(TextileRepository.class);

    //    System.out.println(textileBean.findById(1l));
    //    System.out.println(textileBean.findAll());

    //      Textile textileForSave = Textile.builder()
    //              .code("someCode4")
    //              .name("someName4")
    //              .color("someColor")
    //              .description("someDescription")
    //              .photo("somePhoto")
    //              .isDeleted(false)
    //              .build();
    //
    //    System.out.println(textileBean.save(textileForSave));
//    Textile textileForUpdate =
//        Textile.builder()
//            .id(0)
//            .code("someCode5")
//            .name("someName5")
//            .color("someColor")
//            .description("someDescription")
//            .photo("somePhoto")
//            .changed(new Timestamp(new Date().getTime()))
//            .isDeleted(false)
//            .build();
//
//    System.out.println(textileBean.update(textileForUpdate));
//    Textile textileForDelete =
//        Textile.builder()
//            .id(0)
//            .code("someCode5")
//            .name("someName5")
//            .color("someColor")
//            .description("someDescription")
//            .photo("somePhoto")
//            .changed(new Timestamp(new Date().getTime()))
//            .isDeleted(false)
//            .build();
//
//    System.out.println(textileBean.delete(textileForDelete));

    TextileProductTypeRepository textileProductTypeBean = annotationConfigApplicationContext.getBean(TextileProductTypeRepository.class);

//    System.out.println(textileProductTypeBean.findAll());
    System.out.println(textileProductTypeBean.findById(1l));
  }
}
