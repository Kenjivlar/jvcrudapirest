package com.kenjiv.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenjiv.apirest.apirest.Entities.Product;
import com.kenjiv.apirest.apirest.Repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND WITH the ID: " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product detailsProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND WITH the ID: " + id));
        
        product.setName(detailsProduct.getName());
        product.setPrice(detailsProduct.getPrice());

        return productRepository.save(product);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND WITH the ID: " + id));

        productRepository.delete(product);
        
        return "The Id product: " + id + " was deleted";
    }
}
