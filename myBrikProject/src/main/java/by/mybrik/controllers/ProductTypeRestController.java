package by.mybrik.controllers;

import by.mybrik.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/producttype")
@RequiredArgsConstructor
public class ProductTypeRestController {

    public final ProductTypeService typeService;

}
