package com.example.mongo.demoMongoDB.Controller;

import com.example.mongo.demoMongoDB.Entities.Requerimiento;
import com.example.mongo.demoMongoDB.Services.SharePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SharePointController {

    @Autowired
    SharePointService sharePointService;

    @GetMapping("/sharepoint/{nombreLista}/{idItem}")
    public String getSharePoint(@PathVariable("nombreLista")String nombreLista,
                                @PathVariable("idItem") String idItem) throws Exception {
    return sharePointService.performHttpRequest(nombreLista);
    }

    @PostMapping("/sharepoint")
    public String postSharePoint(@RequestBody Requerimiento requerimiento)throws  Exception{

        return sharePointService.savePostSharepoint(requerimiento);
    }
}
