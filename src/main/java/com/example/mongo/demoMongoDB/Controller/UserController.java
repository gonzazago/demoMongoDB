package com.example.mongo.demoMongoDB.Controller;

import com.example.mongo.demoMongoDB.Entities.Persona;
import com.example.mongo.demoMongoDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    Mono<Persona> registrar(@Valid @RequestBody Persona persona){
        return userRepository.save(persona);
    }




    @GetMapping("/user/{id}")
    Mono<ResponseEntity<Persona>> findById (@PathVariable("id")String id){

        return userRepository.findById(id)
                .map(user->ResponseEntity.ok(user))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/user")
    Flux<Persona> findAll(){
        return userRepository.findAll();
    }

    @PutMapping("/user/{id}")
    public Mono<ResponseEntity<Persona>> editUser(@PathVariable ("id") String id, @Valid @RequestBody Persona persona){
        return userRepository.findById(id)
                .flatMap(existUser -> {
                    existUser.setNombre(persona.getNombre());
                    existUser.setApellido(persona.getApellido());
                    existUser.setEdad(persona.getEdad());
                    return userRepository.save(existUser);
                })
                .map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
