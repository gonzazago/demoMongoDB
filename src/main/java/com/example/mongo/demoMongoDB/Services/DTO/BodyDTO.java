package com.example.mongo.demoMongoDB.Services.DTO;

import com.example.mongo.demoMongoDB.Services.RequestSecurityTokenResponse;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
public class BodyDTO {

    @JacksonXmlProperty(isAttribute = true,localName = "RequestSecurityTokenResponse")
    RequestSecurityTokenResponse requestSecurityTokenResponse;
}
