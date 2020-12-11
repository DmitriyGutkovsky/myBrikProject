package by.mybrik.controllers;

import by.mybrik.controllers.requests.textileRequests.TextileCreate;
import by.mybrik.controllers.requests.textileRequests.TextileUpdate;
import by.mybrik.domain.Textile;
import by.mybrik.repository.impl.TextileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/textile")
public class TextileController {

  public final TextileRepository textileRepository;

  // http://localhost:8080/new/rest/textile
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Textile> getListOfAllTextile() {
    return textileRepository.findAll();
  }

  // http://localhost:8080/new/rest/textile/5
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Textile> findTextileById(@PathVariable("id") Long id) {
    return textileRepository.findById(id);
  }

  // http://localhost:8080/new/rest/textile/6
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<Textile> deleteTextileById(@PathVariable("id") Long id) {

    if (!textileRepository.existsById(id)) {
      // TODO make own Exception
      throw new RuntimeException();
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
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Textile updateTextile(@PathVariable("id") Long id, @RequestBody TextileUpdate request) {
    if (!textileRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
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
}