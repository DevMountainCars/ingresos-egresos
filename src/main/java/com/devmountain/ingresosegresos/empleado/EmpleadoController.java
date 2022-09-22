package com.devmountain.ingresosegresos.empleado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {

        this.empleadoService = empleadoService;
    }

    @GetMapping("/verEmpleados")
    public String listarEmpleados(Model model){
        List<Empleado> empleadoList=empleadoService.getAllEmpleado();
        model.addAttribute("empleadoList",empleadoList);
        return "verEmpleados";
    }

}
