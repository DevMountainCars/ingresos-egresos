package com.devmountain.ingresosegresos.empleado.thymeleaf;

import com.devmountain.ingresosegresos.empleado.Empleado;
import com.devmountain.ingresosegresos.empleado.EmpleadoService;
import com.devmountain.ingresosegresos.empresa.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class EmpleadoTyController {
    private final EmpleadoService empleadoService;

    public EmpleadoTyController(EmpleadoService empleadoService) {

        this.empleadoService = empleadoService;
    }

    @GetMapping("/verEmpleados")
    public String listarEmpleados(Model model){
        List<Empleado> empleadoList=empleadoService.getAllEmpleado();
        model.addAttribute("empleadoList",empleadoList);
        return "verEmpleados";
    }

}
