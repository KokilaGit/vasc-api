package com.vasc.vasc_api.controllers;

import com.vasc.vasc_api.entities.Cart;
import com.vasc.vasc_api.entities.Product;
import com.vasc.vasc_api.repositories.ProductRepository;
import com.vasc.vasc_api.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public  ResponseEntity<Product>addNewProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.addNewProduct(product);
        //setting the location(url) for created product from the database with the id from savedproduct
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/product/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    //getting list of all products
    @GetMapping("/allProducts")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    //getting products by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(this.productService.getProductById(id));
    }


    //update products
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.updateProduct(id, product));
    }

    //Delete products
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> deleteProduct(@PathVariable("id") Integer id) {
        HashMap<String,Object> responseMap = this.productService.deleteProduct(id);
        if(responseMap.get("wasDeleted").equals(false)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }

}
