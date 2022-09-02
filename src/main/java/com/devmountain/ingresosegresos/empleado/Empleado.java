package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.movimiento.Movimiento;
import com.devmountain.ingresosegresos.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleados")
public class Empleado {
    @Id
    @SequenceGenerator(
            name="empleados_id_seq",
            sequenceName="empleados_id_seq",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empleados_id_seq"
    )
    private Integer id;
    private String nombre;
    private String email;
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @OneToMany(mappedBy = "empleado")
    private Set<Movimiento> movimientos;
}
