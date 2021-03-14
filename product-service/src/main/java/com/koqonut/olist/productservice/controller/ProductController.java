package com.koqonut.olist.productservice.controller;

import com.koqonut.olist.productservice.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    @GetMapping()
    public String load(){      
       return productRepository.loadData() ;
    }


    @PostMapping()
    public String getRecords(@RequestParam int numRecords){
        return productRepository.getRecords(numRecords);
       
    }
    
}
