package com.devmountain.ingresosegresos.empleado;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
@Controller
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @GetMapping(value = "/empresas/{id}/empleados")
    public String listarEmpleadosEmpresa(@PathVariable Integer id, Model model) {
        List<EmpleadoDTO> empleados = empleadoService.consultarPorEmpresa(id);
        model.addAttribute("titulo", "Listar empleados");
        model.addAttribute("empleados", empleados);
        return "/empleados/listar";
    }

    @RequestMapping(value = "/empleados/form")
    public String crear(Model model) {
        EmpleadoDTO empleado = new EmpleadoDTO();
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Empleado");

        return "empleados/form";
    }

    public String guardar(@Valid @ModelAttribute("empleado") EmpleadoDTO empleado, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de empleado");
            return "empleados/form";
        }
        if (Objects.isNull(empleado.getId())) {
            empleadoService.crear(empleado);
        } else {
            empleadoService.modificar(empleado);
        }
        return "redirect:/empleados";
    }

    @RequestMapping(value = "/empleados/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        EmpleadoDTO empleado = null;
        if (Objects.isNull(id) || id > 0) {
            empleado = empleadoService.consultarPorId(id);
        } else {
            return "redirect:empleados/listar";
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Editar empleado");
        return "/empleados/form";
    }

}