package com.example.mongo.demoMongoDB.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tarjeta")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tarjeta {
    @Id
    private String id;

    private String name;

    @JsonProperty("idBoard")
    private String tablero;

    @JsonProperty("idList")
    private String lista;



}
