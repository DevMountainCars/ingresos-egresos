package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.rol.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
