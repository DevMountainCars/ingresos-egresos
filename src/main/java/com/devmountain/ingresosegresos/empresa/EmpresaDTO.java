package com.devmountain.ingresosegresos.empresa;

import com.devmountain.ingresosegresos.empleado.EmpleadoDTO;
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
public class EmpresaDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;
    private Set<EmpleadoDTO> empleados;
}
