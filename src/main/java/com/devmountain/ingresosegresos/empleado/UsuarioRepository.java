package com.devmountain.ingresosegresos.empleado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Empleado, Integer> {
        Empleado findByEmail(String email);
}
