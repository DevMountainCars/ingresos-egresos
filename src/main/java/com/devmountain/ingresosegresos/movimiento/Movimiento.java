package com.devmountain.ingresosegresos.movimiento;

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
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @SequenceGenerator(
            name="movimientos_id_seq",
            sequenceName="movimientos_id_seq",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movimientos_id_seq"
    )
    private Integer id;
    private float monto;
    private String concepto;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
