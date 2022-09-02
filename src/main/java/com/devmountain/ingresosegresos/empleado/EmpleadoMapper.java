package com.devmountain.ingresosegresos.empleado;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmpleadoMapper {
    EmpleadoMapper INSTANCE = Mappers.getMapper(EmpleadoMapper.class);
    EmpleadoDTO empleadoToEmpleadoDTO(Empleado empleado);
    Empleado empleadoDTOToEmpleado(EmpleadoDTO empleadoDTO);
}
