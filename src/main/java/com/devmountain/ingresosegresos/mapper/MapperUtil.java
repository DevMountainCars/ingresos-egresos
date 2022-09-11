package com.devmountain.ingresosegresos.mapper;

import com.devmountain.ingresosegresos.empleado.Empleado;
import com.devmountain.ingresosegresos.empleado.EmpleadoDTO;
import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.empresa.EmpresaDTO;
import com.devmountain.ingresosegresos.movimiento.Movimiento;
import com.devmountain.ingresosegresos.movimiento.MovimientoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperUtil {
    MapperUtil INSTANCE = Mappers.getMapper(MapperUtil.class);
    @Mapping(target = "movimientos", ignore = true)
    EmpleadoDTO empleadoToEmpleadoDTO(Empleado empleado);
    Empleado empleadoDTOToEmpleado(EmpleadoDTO empleadoDTO);
    MovimientoDTO movimientoToMovimientoDTO(Movimiento movimiento);
    Movimiento movimientoDTOTOMovimiento(MovimientoDTO movimientoDTO);
    @Mapping(target = "empleados", ignore = true)
    EmpresaDTO empresaToEmpresaDTO(Empresa empresa);
    Empresa empresaDTOTOEmpresa(EmpresaDTO empresaDTO);
}
