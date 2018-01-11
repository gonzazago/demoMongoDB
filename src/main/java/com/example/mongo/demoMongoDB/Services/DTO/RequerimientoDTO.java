package com.example.mongo.demoMongoDB.Services.DTO;

import com.example.mongo.demoMongoDB.Entities.Requerimiento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequerimientoDTO {

    @JsonProperty("d")
    private Requerimiento requerimiento;

}
