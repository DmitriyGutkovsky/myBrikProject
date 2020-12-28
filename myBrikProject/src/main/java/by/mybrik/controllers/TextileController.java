package by.mybrik.controllers;

import by.mybrik.controllers.requests.textileRequests.TextileCreate;
import by.mybrik.controllers.requests.textileRequests.TextileUpdate;
import by.mybrik.domain.ProductType;
import by.mybrik.domain.Textile;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.TextileRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/textile")
public class TextileController {

  public final TextileRepository textileRepository;

  // http://localhost:8080/new/rest/textile
  @ApiOperation(value = "Endpoint for getting a list of all textile")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Textile> getListOfAllTextile() {
    return textileRepository.findAll();
  }

  // http://localhost:8080/new/rest/textile/5
  @ApiOperation(value = "Endpoint for getting a textile with specified id")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Textile> findTextileById(@PathVariable("id") Long id) {
    return textileRepository.findById(id);
  }

  // http://localhost:8080/new/rest/textile/6
  @ApiOperation(value = "Endpoint deleting from Database a specified textile by id")
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
  public List<Textile> deleteTextileById(@PathVariable("id") Long id) {

    if (!textileRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no textile with id = " + id);
    }
    textileRepository.deleteById(id);
    return textileRepository.findAll();
  }

  /*
  http://localhost:8080/new/rest/textile/
  {
      "code": "someCode89",
      "name": "someName69",
      "color": "someColor",
      "description": "someDescription",
      "photo": "somePhoto",
      "deleted": false
  }
   */
  @ApiOperation(value = "Endpoint for adding a new textile")
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
  public Textile addNewTextile(@RequestBody TextileCreate request) {

    Textile textile = new Textile();

    textile.setCode(request.getCode());
    textile.setName(request.getName());
    textile.setColor(request.getColor());
    textile.setDescription(request.getDescription());
    textile.setPhoto(request.getPhoto());
    textile.setDeleted(request.isDeleted());

    return textileRepository.save(textile);
  }

  /*
  http://localhost:8080/new/rest/textile/6
   {
      "code": "someCodeUpdate",
      "name": "someName6",
      "color": "someColor",
      "description": "someDescription",
      "photo": "somePhoto",
      "deleted": false
  }
   */
  @ApiOperation(value = "Endpoint for updating information about textile")
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
  public Textile updateTextile(@PathVariable("id") Long id, @RequestBody TextileUpdate request) {
    if (!textileRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no textile with id = " + id);
    }

    Textile updatedTextile = textileRepository.getOne(id);

    updatedTextile.setCode(request.getCode());
    updatedTextile.setName(request.getName());
    updatedTextile.setColor(request.getColor());
    updatedTextile.setDescription(request.getDescription());
    updatedTextile.setPhoto(request.getPhoto());
    updatedTextile.setDeleted(request.isDeleted());
    updatedTextile.setChanged(new Timestamp(System.currentTimeMillis()));

    return textileRepository.save(updatedTextile);
  }

  @ApiOperation(
      value =
          "Endpoint for  changing status availability for textile: "
              + "if textile is available - isDeleted should be put on false, "
              + "if textile is not available - isDeleted should be put on true.")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping("/changestatus")
  public Textile changeStatus(@RequestParam String code, @RequestParam Boolean status) {

    Textile product = textileRepository.findByCode(code);

    product.setDeleted(status);
    product.setChanged(new Timestamp(System.currentTimeMillis()));

    return textileRepository.save(product);
  }

  @ApiOperation(value = "Endpoint for getting a list of all available textile")
  @GetMapping("/available_textile")
  public ResponseEntity<List<Textile>> findAllAvailableTextile() {
    return new ResponseEntity<>(textileRepository.findAllByIsDeletedIsFalse(), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint for getting a textile  specified by name")
  @GetMapping("/textile_name")
  @ResponseStatus(HttpStatus.OK)
  public Textile findTextileByName(@RequestParam String name) {
    return textileRepository.findByName(name);
  }

  @ApiOperation(value = "Endpoint for getting a list of textile specified by color")
  @GetMapping("/textile_color")
  @ResponseStatus(HttpStatus.OK)
  public List<Textile> findTextileByColor(@RequestParam String color) {
    return textileRepository.findAllByColor(color);
  }

  @ApiOperation(
      value = "Endpoint for getting a list of all available products for specified textile")
  @GetMapping("/available_product_types")
  public ResponseEntity<Set<ProductType>> findAvailableTypes(@RequestParam String name) {
    if (!textileRepository.existsByName(name)) {
      throw new EntityNotFoundException("There is no textile with name = " + name);
    }
    Textile specifiedTextile = textileRepository.findByName(name);
    Set<ProductType> productTypes = specifiedTextile.getProductTypes();
    return new ResponseEntity<>(productTypes, HttpStatus.OK);
  }
}
