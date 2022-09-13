package com.devmountain.ingresosegresos.empresa.thymeleaf;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.empresa.EmpresaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpresaTyController {
    private final EmpresaService empresaService;

    public EmpresaTyController(EmpresaService empresaService) {

        this.empresaService = empresaService;
    }
@GetMapping("/verEmpresa")
    public String listarEmpresa(Model model){
        List<Empresa> emplist=empresaService.getAllEmpresas();
        model.addAttribute("emplist",emplist);
        return "verEmpresa";
    }
}
