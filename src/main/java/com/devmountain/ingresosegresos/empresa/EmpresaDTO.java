package com.devmountain.ingresosegresos.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
