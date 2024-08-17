package com.cjava.demo.service;

import com.cjava.demo.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("cursoService")
public class CursoServiceImpl implements CursoService{

    @Autowired
    RestTemplate clienteRest;
    @Override
    public List<Curso> listar() {
        return clienteRest.getForObject("http://cjava-cursos/cursos", List.class);
    }

    @Override
    public Curso buscar(String id) {
        return null;
    }

    @Override
    public void registrar(Curso curso) {

    }
}
