package by.mybrik.controllers;

import by.mybrik.controllers.requests.textileRequests.TextileCreateRequest;
import by.mybrik.controllers.requests.textileRequests.TextileUpdateRequest;
import by.mybrik.domain.Textile;
import by.mybrik.service.TextileService;
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

    /*
    http://localhost:8080/rest/textile/
    {
        "code": "someCode6",
        "name": "someName6",
        "color": "someColor",
        "description": "someDescription",
        "photo": "somePhoto",
        "deleted": false
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Textile addNewTextile(@RequestBody TextileCreateRequest request){

        Textile textile = new Textile();

        textile.setCode(request.getCode());
        textile.setName(request.getName());
        textile.setColor(request.getColor());
        textile.setDescription(request.getDescription());
        textile.setPhoto(request.getPhoto());
        textile.setDeleted(request.isDeleted());

        return textileService.save(textile);
    }


    /*
    http://localhost:8080/rest/textile/6
     {
        "code": "someCode0",
        "name": "someName6",
        "color": "someColor",
        "description": "someDescription",
        "photo": "somePhoto",
        "deleted": false
    }
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Textile updateTextile(@PathVariable("id") Long id, @RequestBody TextileUpdateRequest request){

        Textile updatedTextile = textileService.findById(id);

        updatedTextile.setCode(request.getCode());
        updatedTextile.setName(request.getName());
        updatedTextile.setColor(request.getColor());
        updatedTextile.setDescription(request.getDescription());
        updatedTextile.setPhoto(request.getPhoto());
        updatedTextile.setDeleted(request.isDeleted());
        updatedTextile.setChanged(new Timestamp(System.currentTimeMillis()));

        return textileService.update(updatedTextile);
    }

}
