package com.hongdu.demo.services;

import com.hongdu.demo.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);
    
    void deleteProduct(Integer id);
}
