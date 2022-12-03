package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Product;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    public Product addProduct(Product product);
    public Product updateProduct(long id, Product product);
    public boolean deleteProduct(long id);
    public List<Product> getAllProduct();
    public List<Product> findByProductName(String productName);
    public Optional<Product> getOneProduct(long id);
}
