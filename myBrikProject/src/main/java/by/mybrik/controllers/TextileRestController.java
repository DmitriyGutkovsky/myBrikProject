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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Textile> getListOfAllTextile(){
        return textileService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Textile findTextileById(@PathVariable("id") Long id){
        return textileService.findById(id);
    }

}
