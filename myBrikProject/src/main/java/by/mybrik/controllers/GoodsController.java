package by.mybrik.controllers;

import by.mybrik.controllers.requests.Comparison;
import by.mybrik.controllers.requests.Criteria;
import by.mybrik.controllers.requests.goodsRequests.GoodsCreate;
import by.mybrik.controllers.requests.goodsRequests.GoodsUpdate;
import by.mybrik.domain.Goods;
import by.mybrik.repository.impl.GoodsRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/goods")
public class GoodsController {

  public final GoodsRepository goodsRepository;

  //  http://localhost:8080/new/rest/goods
  @ApiOperation(value = "Endpoint for getting list of all goods")
  @GetMapping
  public ResponseEntity<List<Goods>> findAllGoods() {
    return new ResponseEntity<>(goodsRepository.findAll(), HttpStatus.OK);
  }

  // http://localhost:8080/new/rest/goods/13
  @ApiOperation(value = "Endpoint for getting specific product by id ")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Goods> findProductById(@PathVariable("id") Long id) {
    return goodsRepository.findById(id);
  }

  //  http://localhost:8080/new/rest/goods/14
  @ApiOperation(value = "Endpoint for hard deleting product from database by id")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<Goods> deleteProduct(@PathVariable Long id) {
    if (!goodsRepository.existsById(id)) {
      // TODO make own Exception
      throw new RuntimeException();
    }
    goodsRepository.deleteById(id);
    return goodsRepository.findAll();
  }

  /*
  POST + http://localhost:8080/new/rest/goods
  {
  "orderCode": "someOrderCode120",
  "name": "cap2",
  "photo": "linkToPhoto",
  "gender": "MALE",
  "size": "52",
  "color": "red",
  "description": "summer cap",
  "price": 15.0,
  "quantity": 15,
  "category": "caps",
  "deleted": false
  }
   */
  @ApiOperation(value = "Endpoint for creating a new product")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Goods createProduct(@RequestBody GoodsCreate createRequest) {

    Goods product = new Goods();

    product.setOrderCode(createRequest.getOrderCode());
    product.setName(createRequest.getName());
    product.setPhoto(createRequest.getPhoto());
    product.setGender(createRequest.getGender());
    product.setSize(createRequest.getSize());
    product.setColor(createRequest.getColor());
    product.setDescription(createRequest.getDescription());
    product.setDeleted(createRequest.isDeleted());
    product.setPrice(createRequest.getPrice());
    product.setQuantity(createRequest.getQuantity());
    product.setCategory(createRequest.getCategory());

    return goodsRepository.save(product);
  }

  /*
  PUT + http://localhost:8080/rest/goods/13
  {
  "orderCode": "someOrderCode123",
  "name": "capBig",
  "photo": "linkToPhoto",
  "gender": "MALE",
  "size": "52",
  "color": "red",
  "description": "summer cap",
  "price": 15.0,
  "quantity": 15,
  "category": "caps",
  "deleted": false
  }
   */
  @ApiOperation(value = "Endpoint for updating specific product by id")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Goods updateProduct(@PathVariable Long id, @RequestBody GoodsUpdate request) {

    if (!goodsRepository.existsById(id)) {
      // TODO make own Exception
      throw new RuntimeException();
    }

    Goods product = goodsRepository.getOne(id);

    product.setId(id);
    product.setOrderCode(request.getOrderCode());
    product.setName(request.getName());
    product.setPhoto(request.getPhoto());
    product.setGender(request.getGender());
    product.setSize(request.getSize());
    product.setColor(request.getColor());
    product.setDescription(request.getDescription());
    product.setDeleted(request.isDeleted());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    product.setCategory(request.getCategory());
    product.setChanged(new Timestamp(System.currentTimeMillis()));

    return goodsRepository.save(product);
  }

  @ApiOperation(
      value =
          "Endpoint for  changing status availability for goods: "
              + "if product is available - isDeleted should be put on false, "
              + "if product is not available - isDeleted should be put on true.")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping("/changestatus")
  public Goods changeStatus(@ModelAttribute Criteria criteria) {

    Goods product = goodsRepository.findByOrderCode(criteria.getParam());

    product.setDeleted(criteria.getCriteria());
    product.setChanged(new Timestamp(System.currentTimeMillis()));

    return goodsRepository.save(product);
  }

  @ApiOperation(value = "Endpoint for getting a list of all available goods")
  @GetMapping("/available_goods")
  public ResponseEntity<List<Goods>> findAllAvailableGoods() {
    return new ResponseEntity<>(goodsRepository.findAllByIsDeletedIsFalse(), HttpStatus.OK);
  }

  @ApiOperation(
      value = "Endpoint for getting a list of all goods with a price less than given price")
  @GetMapping("/goods_with_price_less_than")
  public ResponseEntity<List<Goods>> findAllWithPriceLessThanQuery(@RequestParam Double price) {
    return new ResponseEntity<>(goodsRepository.findAllByPriceLessThanQuery(price), HttpStatus.OK);
  }

  @ApiOperation(
      value = "Endpoint for getting a list of all goods with a price more than given price")
  @GetMapping("/goods_with_price_more_than")
  public ResponseEntity<List<Goods>> findAllWithPriceMoreThanQuery(@RequestParam Double price) {
    return new ResponseEntity<>(goodsRepository.findAllByPriceMoreThanQuery(price), HttpStatus.OK);
  }

  @ApiOperation(
      value = "Endpoint for getting a list of all goods with a price equal to given price")
  @GetMapping("/goods_with_price_equal_to")
  public ResponseEntity<List<Goods>> findAllWithPriceEqualQuery(@RequestParam Double price) {
    return new ResponseEntity<>(goodsRepository.findAllByPriceEqualQuery(price), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint for getting a list of all goods with specified size")
  @GetMapping("/goods_with_size")
  public ResponseEntity<List<Goods>> findAllSizeQuery(
      @RequestParam Comparison comparison, @RequestParam Integer size) {

    switch (comparison) {
      case LESS:
        return new ResponseEntity<>(goodsRepository.findAllBySizeBefore(size), HttpStatus.OK);
      case MORE:
        return new ResponseEntity<>(goodsRepository.findAllBySizeAfter(size), HttpStatus.OK);
      case EQUAL:
        return new ResponseEntity<>(goodsRepository.findAllBySize(size), HttpStatus.OK);
      default:
        return new ResponseEntity<>(goodsRepository.findAll(), HttpStatus.OK);
    }
  }
}
