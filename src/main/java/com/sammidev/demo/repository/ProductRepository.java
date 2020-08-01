package com.sammidev.demo.repository;

import com.sammidev.demo.entity.Product;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCassandraRepository<Product, Integer> {
}
