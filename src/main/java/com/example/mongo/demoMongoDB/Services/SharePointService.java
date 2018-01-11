package com.example.mongo.demoMongoDB.Services;

import com.example.mongo.demoMongoDB.Entities.Requerimiento;
import com.example.mongo.demoMongoDB.Services.DTO.RequerimientoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

@Service
public class SharePointService  {



    private String usr = "gzago";
    private String pass = "Gonza201801";


    @Autowired
    ConsumeSharePoint consumeSharePoint;

    @Autowired
    UtilsCommons utilsCommons;

    @Value("${urlCookiesSignIn}")
    private String urlCookiesSignIn;

    @Value("${urlFormDigestValue}")
    private String urlFormDigestValue;


    public List<String> getSignInCookies() throws Exception {
      return consumeSharePoint.getCookies(urlCookiesSignIn);
    }
    public String getFormDigestValue(List<String> cookies) throws Exception {
        RestTemplate restTemplate = utilsCommons.configSkipCertif();
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", String.join(";",cookies));
        headers.add("Accept", "application/json;odata=verbose");
        headers.add("X-ClientService-ClientTag", "SDK-JAVA");
        RequestEntity<String> requestEntity = new RequestEntity<>(headers,HttpMethod.POST,new URI(urlFormDigestValue));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
        JSONObject json = new JSONObject(responseEntity.getBody());
        return json.getJSONObject("d").getJSONObject("GetContextWebInformation").getString("FormDigestValue");
    }
    public String performHttpRequest(String nombreLista) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("https://cdatecinfo.sharepoint.com/sites/TGS/_api/lists/getbytitle('Requerimientos')/fields?$select=Choices&$filter=Title+eq+'Tecnología'");
        RestTemplate restTemplate = utilsCommons.configSkipCertif();
        List<String> cookies = getSignInCookies();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", String.join(";",cookies));
        headers.add("Content-type", "application/json;odata=verbose");
        headers.add("X-RequestDigest", getFormDigestValue(cookies));
        RequestEntity<String> requestEntity = new RequestEntity<>("", headers, HttpMethod.GET,new URI(sb.toString()));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
        return responseEntity.getBody();

    }


    public String savePostSharepoint(Requerimiento requerimiento) throws  Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("https://cdatecinfo.sharepoint.com/sites/TGS/_api/lists/getbytitle('Requerimientos')/items");
        RestTemplate restTemplate = utilsCommons.configSkipCertif();
        List<String> cookies = getSignInCookies();
        System.out.println(String.join(";",cookies));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", String.join(";",cookies));
        headers.add("Content-type", "application/json;odata=verbose");
        headers.add("X-RequestDigest", getFormDigestValue(cookies));
        ObjectMapper mapper = new ObjectMapper();
        RequerimientoDTO req= new RequerimientoDTO();
        req.setRequerimiento(requerimiento);
        String jsonBody = mapper.writeValueAsString(req);
        RequestEntity<String> requestEntity = new RequestEntity<>(jsonBody, headers, HttpMethod.POST,new URI(sb.toString()));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);

        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();
    }

}
