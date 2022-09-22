package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.EmpresaDTO;
import com.devmountain.ingresosegresos.movimiento.MovimientoDTO;
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
    private EmpresaDTO empresa;
    private Rol rol;
    private String password;
    private Boolean estado;
    private Set<MovimientoDTO> movimientos;
}
