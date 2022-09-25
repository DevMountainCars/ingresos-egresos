package com.devmountain.ingresosegresos.movimiento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MovimientoController {

    private final MovimientoService movimientoService;
}
