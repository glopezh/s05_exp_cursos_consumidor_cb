package com.cjava.demo.service;

import com.cjava.demo.model.Curso;
import com.cjava.demo.wsclient.CursoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cursoServiceFeign")
public class CursoServiceImplFeign implements CursoService{

    @Autowired
    public CursoFeignClient cursoFeign;

    @Override
    public List<Curso> listar() {
        return cursoFeign.listar();
    }


    @Override
    public Curso buscar(String id) {
        return null;
    }

    @Override
    public void registrar(Curso curso) {

    }
}
