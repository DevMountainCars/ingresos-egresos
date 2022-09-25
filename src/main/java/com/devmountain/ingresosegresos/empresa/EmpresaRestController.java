package com.devmountain.ingresosegresos.empresa;

import com.devmountain.ingresosegresos.empleado.EmpleadoDTO;
import com.devmountain.ingresosegresos.empleado.EmpleadoService;
import com.devmountain.ingresosegresos.movimiento.MovimientoDTO;
import com.devmountain.ingresosegresos.movimiento.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restServices/empresa")
public class EmpresaRestController {
    private final EmpresaService empresaService;
    private final EmpleadoService empleadoService;
    private final MovimientoService movimientoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO crear(@RequestBody EmpresaDTO empresaDTO) {
        return empresaService.crear(empresaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmpresaDTO> listar() {
        return empresaService.consultarTodas();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO consultarPorId(Integer id) {
        return empresaService.consultar(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editar(@RequestBody EmpresaDTO empresaDTO) {
        empresaService.modificar(empresaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Integer id) {
        empresaService.eliminar(id);
    }

    @GetMapping(value = "/consultarPorNit")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO consultarPorNit(@RequestParam String nit) {
        return empresaService.consultarPorNit(nit);
    }

    @GetMapping(value = "/{id}/empleados")
    @ResponseStatus(HttpStatus.OK)
    public List<EmpleadoDTO> consultarEmpleados(@PathVariable Integer id) {
        return empleadoService.consultarPorEmpresa(id);
    }

    @GetMapping(value = "/{id}/movimientos")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoDTO> consultarMovimientosEmpresa(Integer id) {
        return movimientoService.consultarMovimientosEmpresa(id);
    }
}
