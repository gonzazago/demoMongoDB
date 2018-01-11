package com.example.mongo.demoMongoDB.Services.DTO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

@Data
@JacksonXmlRootElement(localName= "Envelope")
public class TokenDTO implements Serializable {
    private static final long serialVersionUID = 5886467622277815801L;

    @JacksonXmlProperty(isAttribute = true,localName = "Body")
    private BodyDTO bodyDTO;
}
