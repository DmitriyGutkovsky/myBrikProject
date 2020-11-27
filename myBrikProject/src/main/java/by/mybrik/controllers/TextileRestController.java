package by.mybrik.controllers;

import by.mybrik.service.TextileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/textile")
@RequiredArgsConstructor
public class TextileRestController {

    public final TextileService textileService;

}
