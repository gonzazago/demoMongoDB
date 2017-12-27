package com.example.mongo.demoMongoDB.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Tablero tablero;
    private Lista lista;
    private String idChecklist;

}
