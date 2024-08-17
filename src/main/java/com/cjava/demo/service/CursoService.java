package com.cjava.demo.service;

import com.cjava.demo.model.Curso;

import java.util.List;

public interface CursoService {
    public List<Curso> listar();
    public Curso buscar(String id);
    public void registrar(Curso curso);


}
