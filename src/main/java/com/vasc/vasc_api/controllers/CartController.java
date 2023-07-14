package com.vasc.vasc_api.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vasc.vasc_api.entities.Cart;
import com.vasc.vasc_api.entities.Product;
import com.vasc.vasc_api.services.CartService;
import com.vasc.vasc_api.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

//    @RequestMapping("/product/{id}/products")
//    public List<Cart> getAllProducts(@PathVariable("id") Integer id){
//        return cartService.getAllProducts(id);
//    }
    @PostMapping("/add")
    public ResponseEntity<Cart> addNewCart(@RequestBody ObjectNode cart) {
        Cart savedCart = this.cartService.addNewCart(cart);
        //setting the location(url) for created product from the database with the id from savedCart
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cart/{id}")
                .buildAndExpand(savedCart.getCart_id())
                .toUri();

        return ResponseEntity.created(location).body(savedCart);
    }

    //getting list of all carts
    @GetMapping("/allCarts")
    public ResponseEntity<Iterable<Cart>> getAllCarts() {
        return ResponseEntity.ok(this.cartService.getAllCarts());
    }

    //getting cart by id
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(this.cartService.getCartById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Cart>> getAllCartsByUserId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(cartService.findAllProductByUserId(id));
    }



    //update carts
    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") Integer id, @RequestBody Cart cart) {
        return ResponseEntity.ok(this.cartService.updateCart(id, cart));
    }

    //Delete carts
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> deleteCart(@PathVariable("id") Integer id) {
        HashMap<String,Object> responseMap = this.cartService.deleteCart(id);
        if(responseMap.get("wasDeleted").equals(false)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }
}
