package com.example.mongo.demoMongoDB.Services;

import com.example.mongo.demoMongoDB.Entities.Tarjeta;
import com.example.mongo.demoMongoDB.Repository.TarjetaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.http.auth.Credentials;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TarjetaService {

    private static final Logger logger = LoggerFactory.getLogger(TarjetaService.class);

    @Autowired
    TarjetaRepository tarjetaRepository;

    @Autowired
    UtilsCommons utilsCommons;

    @Value("${urlPreFix}")
    private String prefix;

    @Value("${urlSufix}")
    private String sufix;

    public Flux<Tarjeta> findTarjetas(String idTablero) throws Exception {
        String user = "gzago";
        String pwdProxy = "Gonza201711";
        StringBuilder url = new StringBuilder();
        url.append(prefix);
        url.append(idTablero);
        url.append(sufix);

        HttpURLConnection connection = utilsCommons.config("GET",user,pwdProxy,url.toString());
        try{
            connection.connect();
            System.out.println("Tamaño del archivo a descargar: " + connection.getContentLength() + " bytes");
            String response = new BufferedReader( new InputStreamReader((connection.getInputStream()))).readLine();

            System.out.println("Respuesta: " + response);
            try {
                logger.info("Convirtiendo Respuesta");
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                List<Tarjeta> resp = Arrays.asList(mapper.readValue(response,Tarjeta[].class));

                return tarjetaRepository.saveAll(resp);

            }catch (Exception e){
                throw new  Exception("Ocurrio un error al convertir la respuesta" + e.getMessage());
            }
        }catch (Exception e){
            logger.error(new BufferedReader(
                    new InputStreamReader(connection.getErrorStream())).readLine());

            throw  new Exception( "Ha ocurrido un error: " + new BufferedReader(
                    new InputStreamReader(connection.getErrorStream())).readLine());
        }

    }


}
