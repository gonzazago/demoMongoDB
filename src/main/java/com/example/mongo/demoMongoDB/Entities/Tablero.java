package com.example.mongo.demoMongoDB.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tablero {

    @JsonProperty("idBoard")
    private String idBoard;
}
