package com.devmountain.ingresosegresos.empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository  extends JpaRepository<Empleado, Integer> {

    List<Empleado> findByEmpresaId(Integer idEmpresa);
}