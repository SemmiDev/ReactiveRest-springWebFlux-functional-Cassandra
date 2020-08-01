package com.sammidev.demo.service;

import com.sammidev.demo.entity.Product;
import com.sammidev.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<Product> getAllProduct() {
        return productRepository.findAll()
                .map(p -> {
                    Product product = new Product();
                    product.setId(p.getId());
                    product.setTitle(p.getTitle());
                    return product;
                });
    }

    public Mono<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        Product p = new Product();
        p.setId(new Random().nextInt());
        p.setTitle(product.getTitle());
        return productRepository.save(p)
                .map(p1 -> {
                    Product product1 = new Product();
                    product1.setId(p1.getId());
                    product1.setTitle(p1.getTitle());
                    return product1;
                });
    }

    public Mono<Void> deleteProdct(int id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> update(Mono<Product> product) {

        return product
            .flatMap((p) -> {
               return productRepository.findById(p.getId())
                    .flatMap(product1 -> {
                    product1.setTitle(p.getTitle());
                    return productRepository.save(product1);
               }).map(updatedTeam -> {
                   Product product1 = new Product();
                   product1.setId(updatedTeam.getId());
                   product1.setTitle(updatedTeam.getTitle());
                   return product1;
               });
            });
    }

}
