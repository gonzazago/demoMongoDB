package com.example.mongo.demoMongoDB.Repository;

import com.example.mongo.demoMongoDB.Entities.Persona;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<Persona,String>{

    Mono<Persona> findByNombre(String nombre);
}
