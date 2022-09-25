package com.devmountain.ingresosegresos.movimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByEmpleadoId(Integer idEmpleado);
    List<Movimiento> findByEmpleadoEmpresaId(Integer idEmpresa);
}
