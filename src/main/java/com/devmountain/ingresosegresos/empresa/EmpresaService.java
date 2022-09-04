package com.devmountain.ingresosegresos.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;
    public List<Empresa> getAllEmpresas(){
        return empresaRepository.findAll();
    }
}
