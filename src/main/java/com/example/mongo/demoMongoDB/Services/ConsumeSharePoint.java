package com.example.mongo.demoMongoDB.Services;


import com.example.mongo.demoMongoDB.Services.DTO.TokenDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.*;
import java.util.List;

@Service
public class ConsumeSharePoint {

    @Autowired
    UtilsCommons utilsCommons;

    @Value("${urlTokenSP}")
    private String urlTokenSP;

    public List<String> getCookies(String path ) throws Exception{
        RequestEntity<String>  requestEntity = new RequestEntity<>(createRequestToken(),HttpMethod.POST,new URI(path));
        RestTemplate restTemplate = utilsCommons.configSkipCertif();

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        return cookies;
  }

  public String createRequestToken() throws Exception {
      SAXBuilder saxBuilder = new SAXBuilder();
      File xmlRequestSP = new File("genTokenSharepoint.xml");
      try {
          BufferedReader br = new BufferedReader(new FileReader(xmlRequestSP));
          String line = "";
          StringBuilder sb = new StringBuilder();
          while ((line = br.readLine()) != null) {
              sb.append(line.trim());
          }

          RestTemplate restTemplate = utilsCommons.config();

          RequestEntity<String> requestEntity = new RequestEntity<>(sb.toString(), HttpMethod.POST, new URI(urlTokenSP));
          ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
          String response = responseEntity.getBody();

          ObjectMapper mapper = new XmlMapper();
          mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
          TokenDTO tokenDTO = mapper.readValue(response,TokenDTO.class);


          String token = tokenDTO.getBodyDTO().getRequestSecurityTokenResponse().getRequestedSecurityToken().getBinarySecurityToken().getBinarySecurityToken();

          return token;

//
//
//          String tagToken = response.substring(response.indexOf("<wsse:BinarySecurityToken"), response.indexOf("</wsse:BinarySecurityToken>"));
//          return tagToken.substring(response.indexOf(">") + 2);

      } catch (Exception e) {
          throw new Exception(e.getMessage());
      }
  }

}
