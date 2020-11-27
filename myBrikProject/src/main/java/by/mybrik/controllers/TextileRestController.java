package by.mybrik.controllers;

import by.mybrik.domain.Textile;
import by.mybrik.service.TextileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/textile")
@RequiredArgsConstructor
public class TextileRestController {

    public final TextileService textileService;

    // http://localhost:8080/rest/textile
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Textile> getListOfAllTextile(){
        return textileService.findAll();
    }

    // http://localhost:8080/rest/textile/5
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Textile findTextileById(@PathVariable("id") Long id){
        return textileService.findById(id);
    }

    // http://localhost:8080/rest/textile/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Textile> deleteTextileById(@PathVariable("id") Long id){
        textileService.delete(textileService.findById(id));
        return textileService.findAll();
    }

}
