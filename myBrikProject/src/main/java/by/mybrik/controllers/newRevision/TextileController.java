package by.mybrik.controllers.newRevision;

import by.mybrik.domain.entities.Textile;
import by.mybrik.service.newImplementation.TextileSer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/textile")
public class TextileController {

    public final TextileSer textileService;

    // http://localhost:8080/rest/textile
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Textile> getListOfAllTextile(){
        return textileService.findAll();
    }
}
