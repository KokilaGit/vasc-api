package com.vasc.vasc_api.services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vasc.vasc_api.entities.Cart;
import com.vasc.vasc_api.entities.Product;
import com.vasc.vasc_api.repositories.CartRepository;
import com.vasc.vasc_api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;


    public CartService(final CartRepository cartRepository, final ProductRepository productRepository, ProductService productService){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public  List<Cart> findAllProductByUserId(Integer userId) {

        return cartRepository.findAllProductByUserId(userId);
    }

    //    public List<Cart> getAllProducts(Integer productId){
//        List<Cart> products = new ArrayList<>();
//        cartRepository.findByProductId(productId);
//        products.forEach(products ::add);
//        return products;
//
//
//    }
    public Cart addNewCart(ObjectNode cart){
////        this.cartRepository.save(cart);
//        if (this.cartRepository.existsById(cart.getCart_id())){
//            Cart updateCart = this.getCartById(cart.getCart_id());
//            Set<Product> products = updateCart.getProducts();
//            Product addedProduct = this.productService.getProductById(cart.getProduct_id());
//            products.add(addedProduct);
//            updateCart.setProducts(products);
//            this.cartRepository.save(updateCart);
//            System.out.println("After Update - products" + updateCart.getProducts());
//        }
//        else
//        {
//            System.out.println("products = " + cart.getProducts());
//            this.cartRepository.save(cart);
//            Cart updateCart = this.getCartById(cart.getCart_id());
//            Set<Product> products = updateCart.getProducts();
//            System.out.println("Before Update - products" + products);
//            Product addedProduct = this.productService.getProductById(cart.getProduct_id());
//            products.add(addedProduct);
//            updateCart.setProducts(products);
//            this.cartRepository.save(updateCart);
//            System.out.println("After Update - products" + updateCart.getProducts());
//
//        }
//
////        addedProduct.setCart(cart);
//
////        this.productRepository.save(addedProduct);


//        Product addedProduct = this.productService.getProductById(cart.ge);
//        products.add(addedProduct);
        System.out.println(cart);
        Cart updateCart = new Cart();
        updateCart.setUserId(cart.get("user_id").intValue());
        Product addedProduct = this.productService.getProductById(cart.get("product_id").intValue());
        updateCart.setProduct(addedProduct);
        this.cartRepository.save(updateCart);

//        products.add(addedProduct);
        return updateCart;
//        return this.cartRepository.save(cart);

    }

    //getting list of all carts

    public Iterable<Cart> getAllCarts(){
        return this.cartRepository.findAll();
    }

    //getting carts by id

    public Cart getCartById( Integer id) {
        Optional<Cart> getCartOptional = this.cartRepository.findById(id);
        if(getCartOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found With Id " + id);
        }

        Cart getCart = getCartOptional.get();
        return getCart;
    }

    //update Products

    public Cart updateCart( Integer id , Cart cart){

        Cart cartToUpdate = getCartById(id);

        Cart updatedCart = this.cartRepository.save(cartToUpdate);
        return updatedCart;
    }

    //Delete Carts

    public HashMap<String, Object> deleteCart(Integer id){
        //Returning hashmap as response with string as key and object as value
        // string as whether the Cart was deleted or not with object as Cart details
        HashMap<String, Object> responseMap = new HashMap<>();

        //finding the Cart by id
        Optional<Cart> getCartOptional = this.cartRepository.findById(id);
        //if the Cart doesn't exist, return responseMap with following details.
        if(getCartOptional.isEmpty()){
            responseMap.put("wasDeleted", false);
            responseMap.put("cartInfo", null);
            responseMap.put("Message","Cart not found with id: " +id);

            return responseMap;
        }

        //if the Cart is found and deleted. send the responseMap with following details
        responseMap.put("wasDeleted", true);
        responseMap.put("cartInfo",getCartOptional.get() );

        Cart deletedCart = getCartOptional.get();
        this.cartRepository.delete(deletedCart);
        return responseMap;




    }

}
