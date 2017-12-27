package com.example.mongo.demoMongoDB.Services;

import com.example.mongo.demoMongoDB.Entities.Tarjeta;
import com.example.mongo.demoMongoDB.Repository.TarjetaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.CompletableFuture;

@Service
public class TarjetaService {

    private static final Logger logger = LoggerFactory.getLogger(TarjetaService.class);

    @Autowired
    TarjetaRepository tarjetaRepository;

    @Value("${urlPreFix}")
    private String prefix;

    @Value("${urlSufix}")
    private String sufix;


    /*public TarjetaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }*/

    @Async
    public CompletableFuture<Tarjeta> findTarjetas(String idTablero) throws Exception {

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.99",3128));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);


        RestTemplate restTemplate =  new RestTemplate(requestFactory);
        logger.info("Invocando a la api trello");

        ResponseEntity<Tarjeta> tarjetaResponseEntita = null;

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(idTablero);
        sb.append(sufix);

        try {

            //tarjeta = restTemplate.getForObject(sb.toString(),Tarjeta.class,idTablero);
             tarjetaResponseEntita = restTemplate.exchange(sb.toString(), HttpMethod.GET,tarjetaResponseEntita,Tarjeta.class);

             tarjetaRepository.save(tarjetaResponseEntita.getBody());
            return CompletableFuture.completedFuture(tarjetaResponseEntita.getBody());
        }catch (Exception e){
            logger.error("Ha ocurrido un error" + e.getMessage());
            logger.error("Codigo de retorno" + tarjetaResponseEntita.getStatusCodeValue() + "Mensaje: " + tarjetaResponseEntita.getBody());

            throw new Exception( tarjetaResponseEntita.getBody().toString());
        }
    }
}
