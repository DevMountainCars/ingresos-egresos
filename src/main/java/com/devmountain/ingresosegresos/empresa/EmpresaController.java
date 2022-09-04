package com.devmountain.ingresosegresos.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpresaController {
    @Autowired
    EmpresaService  empresaService;
    @GetMapping("/empresa")
    public List<Empresa> verEmpresa(){
        return empresaService.getAllEmpresas();
    }
}
