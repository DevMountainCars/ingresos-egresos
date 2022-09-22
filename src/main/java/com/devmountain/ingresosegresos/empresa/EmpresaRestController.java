package com.devmountain.ingresosegresos.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpresaRestController {
    @Autowired
    EmpresaService  empresaService;
    @GetMapping("/VerEmpresa")
    public List<Empresa> verEmpresa(){
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/empresa")
    public boolean guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }
    @GetMapping(path = "empresa/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresaById(id);
    }
    @PatchMapping("/empresa/{id}")
    public boolean actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa emp= empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNit(empresa.getNit());
        return empresaService.saveOrUpdateEmpresa(emp);

    }
    @DeleteMapping (path= "empresa/{id}")
    public String DeleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= this.empresaService.deleteEmpresa(id);
        if (respuesta){  //Si respuesta es true?
            return "Se eliminó la empresa con id" +id;
        }
        else{
            return "No se pudo eliminar la empresa con id"+id;
        }
    }

    @GetMapping(path = "empresa/{id}/movimientos")
    public Empresa movimientosPorEmpresa(@PathVariable("id") Integer id){
        return new Empresa();
    }
}
