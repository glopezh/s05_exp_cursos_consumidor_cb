package com.cjava.demo.controllers;

import com.cjava.demo.model.Curso;
import com.cjava.demo.service.CursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class CursoController {
    private static Logger log = LoggerFactory.getLogger(CursoController.class);

    @Qualifier("cursoServiceFeign")
    @Autowired
    CursoService cursoService;

    @Autowired
    private Environment env;

    @Value("${configuracion.mensaje}")
    private String texto;

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/cursos")
    public List<Curso> getCursos(){
        //return cursoService.listar();
        return cbFactory.create("cbPrueba1")
                .run(()-> cursoService.listar(), e -> metodoAlternativo(e));
    }

    public List<Curso> metodoAlternativo(Throwable e) {
        String url ="http://cjava-cursos-respaldo/cursos";
        return restTemplate.getForObject(url,List.class);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
        log.info(texto);

        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", env.getProperty("configuracion.autor.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
}
