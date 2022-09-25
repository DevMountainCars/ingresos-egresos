package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.empresa.EmpresaRepository;
import com.devmountain.ingresosegresos.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;

    public List<EmpleadoDTO> consultarTodos() {
        return empleadoRepository
                .findAll()
                .stream()
                .map(MapperUtil.INSTANCE::empleadoToEmpleadoDTO)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO crear(EmpleadoDTO empleadoDTO) {
        if (Objects.nonNull(empleadoDTO)) {
            Optional<Empleado> empleado = empleadoRepository.findById(empleadoDTO.getId());
            if (empleado.isPresent()) {
                throw new IllegalArgumentException(
                        String.format("Error al crear empleado, existe un empleado registrado con ID %s",
                                empleadoDTO.getId())
                );
            }
            empleadoDTO.setId(null);
        }
        Empleado empleado = empleadoRepository.save(MapperUtil.INSTANCE.empleadoDTOToEmpleado(empleadoDTO));
        return MapperUtil.INSTANCE.empleadoToEmpleadoDTO(empleado);
    }

    public EmpleadoDTO consultarPorId(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Error al consultar empleado, el id es un valor nulo");
        }

        Empleado empleado = empleadoRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No se encontró usuario con id %s", id)
                ));
        return MapperUtil.INSTANCE.empleadoToEmpleadoDTO(empleado);
    }

    @Transactional
    public void modificar(EmpleadoDTO empleadoDTO) {
        if (Objects.isNull(empleadoDTO)) {
            throw new IllegalArgumentException("Error al editar una empresa, datos nulos.");
        }
        if (Objects.isNull(empleadoDTO.getId())) {
            throw new IllegalArgumentException("Error al editar editar una empresa, ID nulo.");
        }

        Empleado empleado = empleadoRepository
                .findById(empleadoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error al editar empleado, no existe un empleado con ID %s", empleadoDTO.getId())
                ));

        if (StringUtils.isNoneBlank(empleadoDTO.getEmail())) {
            empleado.setEmail(empleadoDTO.getEmail());
        }
        if (StringUtils.isNoneBlank(empleadoDTO.getNombre())) {
            empleado.setNombre(empleadoDTO.getNombre());
        }
        if (Objects.nonNull(empleadoDTO.getRol())) {
            empleado.setRol(empleadoDTO.getRol());
        }
        if (Objects.nonNull(empleadoDTO.getEmpresa())) {
            empleado.setEmpresa(MapperUtil.INSTANCE.empresaDTOTOEmpresa(empleadoDTO.getEmpresa()));
        }
    }

    public void eliminar(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Error al eliminar empleado, id nulo.");
        }

        Empleado empleado = empleadoRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error al eliminar empleado, no existe un empleado con ID %s", id)
                ));

        empleadoRepository.delete(empleado);
    }

    public List<EmpleadoDTO> consultarPorEmpresa(Integer idEmpresa) {
        if (Objects.isNull(idEmpresa)) {
            throw new IllegalArgumentException("Error al consultar empleados por empresa, ID empres es un valor nulo");
        }

        if (!empresaRepository.existsById(idEmpresa)) {
            throw new IllegalArgumentException(String.format("La empresa con ID %s no se encuentra registrada.",
                    idEmpresa));
        }

        List<EmpleadoDTO> empleadoDTOList = empleadoRepository
                .findByEmpresaId(idEmpresa)
                .stream()
                .map(MapperUtil.INSTANCE::empleadoToEmpleadoDTO)
                .collect(Collectors.toList());

        if (empleadoDTOList.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("No se encontraron empleados registrados para la empresa con ID %s", idEmpresa)
            );
        }

        return empleadoDTOList;
    }

    public List<EmpleadoDTO> consultarPorEmpresa(String nit) {
        if (StringUtils.isBlank(nit)) {
            throw new IllegalArgumentException("Error al consultar empleados, el nit de la empresa es un valor nulo.");
        }
        Empresa empresa = empresaRepository
                .findByNit(nit)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una empresa registrada con nit %s"));
        return consultarPorEmpresa(empresa.getId());
    }

}
