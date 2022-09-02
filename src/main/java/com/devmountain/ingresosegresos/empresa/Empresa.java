package com.devmountain.ingresosegresos.empresa;

import com.devmountain.ingresosegresos.empleado.Empleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @SequenceGenerator(
            name="empresas_id_seq",
            sequenceName="empresas_id_seq",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empresas_id_seq"
    )
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;
    @OneToMany(mappedBy = "empresa")
    private Set<Empleado> empleados;
}
