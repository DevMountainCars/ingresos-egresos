package com.devmountain.ingresosegresos.empleado;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
    public interface EmpleadoRepository  extends CrudRepository<Empleado, Integer> {
        @Query(value="SELECT * FROM empleados e WHERE e.id_empresa = ?1", nativeQuery=true)

        public abstract ArrayList<Empleado> findByEmpresa(Integer id);
}
