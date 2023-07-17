package com.vasc.vasc_api.services;

import com.vasc.vasc_api.entities.Product;
import com.vasc.vasc_api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(final ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public Product addNewProduct( Product product){
        return this.productRepository.save(product);
    }

    //getting list of all products

    public Iterable<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    //getting Products by id

    public Product getProductById( Integer id) {
        Optional<Product> getProductOptional = this.productRepository.findById(id);
        if(getProductOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found With Id " + id);
        }

        Product getProduct = getProductOptional.get();
        return getProduct;
    }

    //update Products

    public Product updateProduct( Integer id , Product product){

        Product productToUpdate = getProductById(id);
        if(product.getTitle() != null){
            productToUpdate.setTitle(product.getTitle());
        }
        if(product.getDescription() != null){
            productToUpdate.setDescription(product.getDescription());
        }
        if(product.getImageName() != null){
            productToUpdate.setImageName(product.getImageName());
        }
        if(product.getPrice() != null){
            productToUpdate.setPrice(product.getPrice());
        }
        Product updatedProduct = this.productRepository.save(productToUpdate);
        return updatedProduct;
    }

    //Delete Products

    public HashMap<String, Object> deleteProduct(Integer id){
        //Returning hashmap as response with string as key and object as value
        // string as whether the Product was deleted or not with object as Product details
        HashMap<String, Object> responseMap = new HashMap<>();

        //finding the Product by id
        Optional<Product> getProductOptional = this.productRepository.findById(id);
        //if the Product doesn't exist, return responseMap with following details.
        if(getProductOptional.isEmpty()){
            responseMap.put("wasDeleted", false);
            responseMap.put("productInfo", null);
            responseMap.put("Message","Product not found with id: " +id);

            return responseMap;
        }

        //if the Product is found and deleted. send the responseMap with following details
        responseMap.put("wasDeleted", true);
        responseMap.put("productInfo",getProductOptional.get() );

        Product deletedProduct = getProductOptional.get();
        this.productRepository.delete(deletedProduct);
        return responseMap;




    }


}
