package com.example.mongo.demoMongoDB.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection = "tweets")
@Data
public class Tweets {

    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String tweet;

    @NotNull
    private Date fechaCreated;

    private List<Persona> likes;
}
