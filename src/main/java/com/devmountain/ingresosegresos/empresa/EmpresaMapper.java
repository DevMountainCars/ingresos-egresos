package com.devmountain.ingresosegresos.empresa;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);
    EmpresaDTO empresaToEmpresaDTO(Empresa empresa);
    Empresa empresaDTOTOEmpresa(EmpresaDTO empresaDTO);
}
