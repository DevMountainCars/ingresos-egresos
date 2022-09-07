package com.devmountain.ingresosegresos.movimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    @Query("SELECT m FROM Movimiento m WHERE m.empleado.id = ?1")
    List<Movimiento> consultarMovimientosPorIdEmpleado(Integer idEmpleado);
}
