package com.devmountain.ingresosegresos.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    @ResponseStatus(HttpStatus.OK)
    public List<EmpleadoDTO> verEmpleados(){
        return empleadoService.getAllEmpleados();
    }

    @PostMapping("/empleados")
    public Optional<EmpleadoDTO> guardarEmpleado(@RequestBody EmpleadoDTO empl){
        return Optional.ofNullable(this.empleadoService.guardarNuevoEmpleado(empl));
    }
    @GetMapping(path = "empleados/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpleadoDTO empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }
    @GetMapping("/enterprises/{id}/empleados")
    public List<EmpleadoDTO> empleadoPorEmpresa(@PathVariable("id") Integer id){
        return empleadoService.obtenerPorEmpresa(id);
    }
    @PatchMapping("/empleados/{id}")
    public void actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody EmpleadoDTO empleadoDTO){
        empleadoService.actualizarEmpleado(empleadoDTO);
    }
    @DeleteMapping("/empleados/{id}")
    public String deleteEmpleado(@PathVariable("id") Integer id){
        if (empleadoService.deleteEmpleado(id)){
            return "Se pudo eliminar correctamente el empleado con id "+id;
        }
        return "No se puedo eliminar correctamente el empleado con id "+id;
    }
}
