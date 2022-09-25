package com.devmountain.ingresosegresos.empresa;

import com.devmountain.ingresosegresos.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public List<EmpresaDTO> consultarTodas() {
        return empresaRepository
                .findAll()
                .stream()
                .map(MapperUtil.INSTANCE::empresaToEmpresaDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO crear(EmpresaDTO empresaDTO) {
        if (Objects.nonNull(empresaDTO.getId())) {
            Optional<Empresa> empresa = empresaRepository.findById(empresaDTO.getId());
            if (empresa.isPresent()) {
                throw new IllegalArgumentException(
                        String.format("Error al crear empresa, ya existe una empresa registrada con ID %s",
                                empresaDTO.getId())
                );
            }
            empresaDTO.setId(null);
        }

        validarNoExistenciaNit(empresaDTO.getNit());

        Empresa empresa = MapperUtil.INSTANCE.empresaDTOTOEmpresa(empresaDTO);
        return MapperUtil.INSTANCE.empresaToEmpresaDTO(empresaRepository.save(empresa));
    }

    public EmpresaDTO consultar(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Error al consultar empresa, el ID no puede ser un valor nulo.");
        }

        Empresa empresa = empresaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No se encontró una empresa con ID %s", id)
                ));
        return MapperUtil.INSTANCE.empresaToEmpresaDTO(empresa);
    }

    @Transactional
    public void modificar(EmpresaDTO empresaDTO) {
        if (Objects.isNull(empresaDTO)) {
            throw new IllegalArgumentException("Error al editar una empresa, datos nulos.");
        }
        if (Objects.isNull(empresaDTO.getId())) {
            throw new IllegalArgumentException("Error al editar editar una empresa, ID nulo.");
        }

        Empresa empresa = empresaRepository
                .findById(empresaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error al editar empresa, no existe una empresa con ID %s", empresaDTO.getId())
                ));
        if (StringUtils.isNoneBlank(empresaDTO.getNombre())) {
            empresa.setNombre(empresaDTO.getNombre());
        }
        if (StringUtils.isNoneBlank(empresaDTO.getNit()) &&
        !empresaDTO.getNit().equals(empresa.getNit())) {
            validarNoExistenciaNit(empresaDTO.getNit());
            empresa.setNit(empresaDTO.getNit());
        }
        if (StringUtils.isNoneBlank(empresaDTO.getDireccion())) {
            empresa.setDireccion(empresaDTO.getDireccion());
        }
        if (StringUtils.isNoneBlank(empresaDTO.getTelefono())) {
            empresa.setTelefono(empresaDTO.getTelefono());
        }
    }

    public void eliminar(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Error al eliminar empresa, id nulo.");
        }

        Empresa empresa = empresaRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error al eliminar empresa, no existe una empresa con ID %s", id)
                ));

        empresaRepository.delete(empresa);
    }

    public EmpresaDTO consultarPorNit(String nit) {
        if (StringUtils.isBlank(nit)) {
            throw new IllegalArgumentException("Error al consultar empresa, el nit es un valor nulo");
        }

        return MapperUtil.INSTANCE.empresaToEmpresaDTO(
                empresaRepository
                        .findByNit(nit)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        String.format("No se encontró una empresa con nit %s", nit)
                                )
                        )
        );
    }

    private void validarNoExistenciaNit(String nit) {
        if (empresaRepository.findByNit(nit).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("El Nit %s ya se encuentra registrado.", nit)
            );
        }
    }
}
