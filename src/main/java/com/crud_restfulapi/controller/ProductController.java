package com.crud_restfulapi.controller;

import com.crud_restfulapi.model.responseObject;
import com.crud_restfulapi.model.Product;
import com.crud_restfulapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping
    List<Product> getAllProducts(){
        return iProductService.getAllProduct();
    }
    @GetMapping("/{id}")
    ResponseEntity<responseObject> findById(@PathVariable long id){
        Optional<Product> foundProduct= iProductService.getOneProduct(id);
        if (foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new responseObject("Done!","Da tim thay id="+id,foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new responseObject("Fail","Khong tim thay id="+id,""));
        }
    }
    //Postman: raw,json
    @PostMapping("/insert")
    ResponseEntity<responseObject> insertProduct(@RequestBody Product product){
        List<Product> products = iProductService.findByProductName(product.getProductName());
        if (products.size()==0) {
            iProductService.addProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new responseObject("Done!", "Da them Product", product)
            );
        } else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new responseObject("Fail", "Product da ton tai", "")
        );}
    }
    @DeleteMapping("/{id}")
    ResponseEntity<responseObject> deleteById(@PathVariable long id){
        Optional<Product> foundProduct= iProductService.getOneProduct(id);

        if (foundProduct.isPresent()){
            iProductService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new responseObject("Done!","Da xoa id="+id,foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new responseObject("Fail","Khong tim thay id="+id,""));
        }
    }
    @PutMapping("{id}")
    ResponseEntity<responseObject> updateProduct(@PathVariable long id,@RequestBody Product product ){
        Optional<Product> foundProduct = iProductService.getOneProduct(id);
        if (foundProduct.isPresent()){
            iProductService.updateProduct(id,product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new responseObject("Done!","Da cap nhat id="+id,product)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new responseObject("Fail","Khong tim thay id="+id,""));
        }
    }
}
