package com.example.mongo.demoMongoDB.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Requerimiento {

    @JsonProperty("__metadata")
    private Metadata metadata;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("SWF")
    private String swf;

    @JsonProperty("Tecnología")
    private String tecnologia;

    @JsonProperty("Desarrollo")
    private String desarrollo;

    @JsonProperty("ID Cliente")
    private String idCliente;

    @JsonProperty("Estimacion al  ")
    private String estmimacion;

//    @JsonProperty("Titulo")
//    private String titulo;

    @JsonProperty("Aplicativo")
    private String aplicativo;

    @JsonProperty("Línea")
    private String linea;

//    @JsonProperty("Frente")
//    private String frente;

    @JsonProperty("Solicitante")
    private String solicitante;

    @JsonProperty("Resp por soli")
    private String respSolici;

    @JsonProperty("Recepcion SWF")
    private Date recepcion;

    @JsonProperty("Estado")
    private String estado;


}
