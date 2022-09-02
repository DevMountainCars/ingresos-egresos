package com.devmountain.ingresosegresos.movimiento;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimientoMapper {
    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);
    MovimientoDTO movimientoToMovimientoDTO(Movimiento movimiento);
    Movimiento movimientoDTOTOMovimiento(MovimientoDTO movimientoDTO);
}
