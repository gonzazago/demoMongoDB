package com.example.mongo.demoMongoDB.Controller;

import com.example.mongo.demoMongoDB.Entities.Tarjeta;
import com.example.mongo.demoMongoDB.Services.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class TarjetaController {

    @Autowired
    TarjetaService tarjetaService;

    @GetMapping("/tarjetas/{id}")
    public Flux<Tarjeta> getTarjeta(@PathVariable("id") String idTablero) throws Exception {

        Flux<Tarjeta> tarjeta = tarjetaService.findTarjetas(idTablero);

        return  tarjeta;
    }


}
