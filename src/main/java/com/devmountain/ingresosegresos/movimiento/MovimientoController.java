package com.devmountain.ingresosegresos.movimiento;

import com.devmountain.ingresosegresos.empresa.EmpresaDTO;
import com.devmountain.ingresosegresos.empresa.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Objects;

@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
@Controller
public class MovimientoController {

    private final MovimientoService movimientoService;
    private final EmpresaService empresaService;

    @GetMapping(value = "/movimientos/{id}/empresa")
    public String listarPorEmpresa(@PathVariable Integer id, Model model) {
        EmpresaDTO empresa = empresaService.consultar(id);
        model.addAttribute("movimientos", movimientoService.consultarMovimientosEmpresa(id));
        model.addAttribute("titulo",
                String.format("Lista de movimientos de la empresa %s", empresa.getNombre()));
        return "/movimientos/listar";
    }

    @RequestMapping(value = "/movimientos/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        MovimientoDTO movimiento = movimientoService.consultarPorId(id);
        model.addAttribute("movimiento", movimiento);
        model.addAttribute("titulo", "Editar movimiento");
        return "/movimientos/editar";
    }

    @PostMapping(value = "/movimientos/guardar")
    public String guardar(@Valid @ModelAttribute("movimiento") MovimientoDTO movimiento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar movimiento");
            return "/movimientos/editar";
        }
        if (Objects.isNull(movimiento.getId())) {
            movimientoService.agregarMovimiento(movimiento);
        } else {
            movimientoService.actualizarMovimiento(movimiento);
        }
        model.addAttribute("titulo", "Editar movimiento");
        model.addAttribute("movimiento", movimiento);
        return "/movimientos/editar";
    }
}
