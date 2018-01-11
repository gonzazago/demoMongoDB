package com.example.mongo.demoMongoDB.Services.DTO;

import com.example.mongo.demoMongoDB.Entities.Tarjeta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TarjetaDTO {

    List<Tarjeta> tarjeta ;
}
