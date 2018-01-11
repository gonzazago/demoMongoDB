package com.example.mongo.demoMongoDB.Services.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Data

public class BinarySecurityToken implements Serializable {
    private static final long serialVersionUID = 5886467622277815801L;
    @JacksonXmlText(value = true)
    private String binarySecurityToken;
}
