package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Product;
import com.crud_restfulapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        productRepository.save(product);
        return null;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        productRepository.findById(id).map(product1 -> {
           product1.setProductName(product.getProductName());
           product1.setPrice(product.getPrice());
           product1.setYear(product.getYear());
           product1.setUrl(product.getUrl());
            return productRepository.save(product1);
        });
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        productRepository.deleteById(id);
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Optional<Product> getOneProduct(long id) {
        return productRepository.findById(id);
    }
}
