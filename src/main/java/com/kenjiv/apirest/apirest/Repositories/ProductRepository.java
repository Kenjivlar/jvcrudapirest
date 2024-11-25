package com.kenjiv.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenjiv.apirest.apirest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
