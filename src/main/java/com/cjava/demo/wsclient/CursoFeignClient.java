package com.cjava.demo.wsclient;

import com.cjava.demo.model.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cjava-cursos")
public interface CursoFeignClient {

    @GetMapping("/cursos")
    public List<Curso> listar();


}
