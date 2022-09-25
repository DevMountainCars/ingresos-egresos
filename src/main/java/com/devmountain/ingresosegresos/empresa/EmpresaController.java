package com.devmountain.ingresosegresos.empresa;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERARIO')")
    @GetMapping
    public String listarEmpresas(Model model) {
        List<EmpresaDTO> empresas = empresaService.consultarTodas();
        model.addAttribute("empresas", empresas);
        model.addAttribute("titulo", "Listar empresas");
        return "empresas/listar";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/form")
    public String crear(Model model) {
        EmpresaDTO empresa = new EmpresaDTO();
        model.addAttribute("empresa", empresa);
        model.addAttribute("titulo", "Formulario empresas");
        return "empresas/form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        EmpresaDTO empresa = null;
        if (Objects.isNull(id) || id > 0) {
            empresa = empresaService.consultar(id);
        } else {
            return "redirect:empresas/listar";
        }
        model.addAttribute("empresa", empresa);
        model.addAttribute("titulo", "Editar empresa");
        return "empresas/form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/form")
    public String guardar(@Valid @ModelAttribute("empresa") EmpresaDTO empresa, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de empresa");
            return "empresas/form";
        }
        if (Objects.isNull(empresa.getId())) {
            empresaService.crear(empresa);
        } else {
            empresaService.modificar(empresa);
        }
        return "redirect:/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        empresaService.eliminar(id);

        flash.addFlashAttribute("success", String.format("La empresa con ID %s fue eliminada.", id));

        return "redirect:/empresas";
    }
}
