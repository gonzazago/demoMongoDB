package com.example.mongo.demoMongoDB.Repository;

import com.example.mongo.demoMongoDB.Entities.Tarjeta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends ReactiveMongoRepository<Tarjeta,String> {
}
