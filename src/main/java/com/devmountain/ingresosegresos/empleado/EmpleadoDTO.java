package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.movimiento.Movimiento;
import com.devmountain.ingresosegresos.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String email;
    private Empresa empresa;
    private Rol rol;
    private Set<Movimiento> movimientos;
}
