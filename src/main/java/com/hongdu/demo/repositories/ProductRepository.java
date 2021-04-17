package com.hongdu.demo.repositories;


import org.springframework.data.repository.CrudRepository;

import com.hongdu.demo.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
}
