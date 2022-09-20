package com.devmountain.ingresosegresos.movimiento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MovimientoController {
    private final MovimientoService movimientoService;

    @GetMapping(value = "movimientos/")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoDTO> listarMovimientos() {
        return movimientoService.listarMovimientos();
    }

    @PostMapping(value = "movimientos/registrar")
    @ResponseStatus(HttpStatus.OK)
    public MovimientoDTO registrarNuevoMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientoDTO = movimientoService.agregarMovimiento(movimientoDTO);
        return movimientoDTO;
    }

    @PatchMapping(value = "movimientos/actualizar")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientoService.actualizarMovimiento(movimientoDTO);
    }

    @DeleteMapping(value = "movimientos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarMovimiento(@PathVariable Integer id) {
        movimientoService.eliminarMovimiento(id);
    }

    @GetMapping("empresa/{id}/movimientos")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoDTO> listarMovimientosEmpresa(@PathVariable(value = "id") Integer idEmpresa) {
        return movimientoService.listarMovimientosEmpresa(idEmpresa);
    }
}
