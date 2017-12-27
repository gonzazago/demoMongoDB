package com.example.mongo.demoMongoDB.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "persona")
@Data
public class Persona {

    @Id
    private String id;
    @Indexed(name = "nombre_index", direction = IndexDirection.ASCENDING)
    private String nombre;
    @NotNull
    private String apellido;
    private Integer edad;
}
