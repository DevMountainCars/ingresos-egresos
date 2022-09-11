package com.devmountain.ingresosegresos.movimiento;

import com.devmountain.ingresosegresos.empleado.EmpleadoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoDTO implements Serializable {
    private Integer id;
    private float monto;
    private String concepto;
    private EmpleadoDTO empleado;
}
