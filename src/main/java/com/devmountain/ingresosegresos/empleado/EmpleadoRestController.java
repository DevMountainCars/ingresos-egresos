package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.movimiento.MovimientoDTO;
import com.devmountain.ingresosegresos.movimiento.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/restService/empleado")
public class EmpleadoRestController {
    private final EmpleadoService empleadoService;
    private final MovimientoService movimientoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmpleadoDTO> listarTodos() {
        return empleadoService.consultarTodos();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpleadoDTO consultar(@PathVariable Integer id) {
        return empleadoService.consultarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoDTO crear(EmpleadoDTO empleadoDTO) {
        return empleadoService.crear(empleadoDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editar(EmpleadoDTO empleadoDTO) {
        empleadoService.modificar(empleadoDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void eliminar(Integer id) {
        empleadoService.eliminar(id);
    }

    @GetMapping(value = "/{id}/movimientos")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoDTO> consultarMovimientosEmpleado(Integer id) {
        return movimientoService.consultarMovimientosEmpleado(id);
    }
}
