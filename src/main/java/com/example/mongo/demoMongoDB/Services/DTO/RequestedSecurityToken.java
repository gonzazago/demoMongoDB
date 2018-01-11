package com.example.mongo.demoMongoDB.Services.DTO;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import java.io.Serializable;

@Data

public class RequestedSecurityToken implements Serializable {
    private static final long serialVersionUID = 5886467622277815801L;
    @JacksonXmlProperty(localName = "BinarySecurityToken" , isAttribute = true)
    private BinarySecurityToken binarySecurityToken;

}
