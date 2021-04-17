package com.hongdu.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongdu.demo.domain.Product;
import com.hongdu.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

	@Override
	public Product getProductById(Integer id) {
		 return productRepository.findById(id).get();
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
		
	}
}
