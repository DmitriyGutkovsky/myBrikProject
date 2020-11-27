package by.mybrik.controllers;

import by.mybrik.domain.Textile;
import by.mybrik.service.TextileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
