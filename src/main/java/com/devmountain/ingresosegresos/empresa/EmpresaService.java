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

    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }
    public Empresa saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp=empresaRepository.save(empresa);
        return emp;
    }
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);

        if (empresaRepository.findById(id)!=null){
            return true;
        }
        return false;
    }
}
