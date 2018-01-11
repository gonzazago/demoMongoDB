package com.example.mongo.demoMongoDB.Services;

import com.example.mongo.demoMongoDB.Services.DTO.RequestedSecurityToken;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


import java.io.Serializable;

@Data

public class RequestSecurityTokenResponse implements Serializable {
    private static final long serialVersionUID = 5886467622277815801L;

    @JacksonXmlProperty(isAttribute = true,localName = "RequestedSecurityToken")
    RequestedSecurityToken requestedSecurityToken;
}
