package com.devmountain.ingresosegresos.empresa;

import com.devmountain.ingresosegresos.empleado.EmpleadoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDTO implements Serializable {
    private Integer id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String direccion;
    @NotEmpty
    private String telefono;
    @NotEmpty
    private String nit;
}
