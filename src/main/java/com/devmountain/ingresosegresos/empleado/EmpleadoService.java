package com.devmountain.ingresosegresos.empleado;

import com.devmountain.ingresosegresos.empresa.Empresa;
import com.devmountain.ingresosegresos.empresa.EmpresaRepository;
import com.devmountain.ingresosegresos.error.DevMountainError;
import com.devmountain.ingresosegresos.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;

    public List<EmpleadoDTO> getAllEmpleados() {
        List<EmpleadoDTO> empleadoDTOList = new ArrayList<>();
        empleadoRepository
                .findAll()
                .iterator()
                .forEachRemaining(empleado -> empleadoDTOList
                        .add(MapperUtil
                                .INSTANCE
                                .empleadoToEmpleadoDTO(empleado)));
        if (empleadoDTOList.isEmpty()) {
            throw new DevMountainError("No se encontraron empleados registrados");
        }
        return empleadoDTOList;
    }
    public EmpleadoDTO getEmpleadoById(Integer id) {
        return empleadoRepository
                .findById(id)
                .map(MapperUtil.INSTANCE::empleadoToEmpleadoDTO)
                .orElseThrow(() -> new DevMountainError(
                        String.format("No se encontró un empleado con ID %s", id)
                ));
    }
    public List<EmpleadoDTO> obtenerPorEmpresa(Integer id){
        Empresa empresa = empresaRepository
                .findById(id)
                .orElseThrow(() -> new DevMountainError(
                String.format("No se encontró una empresa con ID %s", id)
        ));
        return empresa
                .getEmpleados()
                .stream()
                .map(MapperUtil.INSTANCE::empleadoToEmpleadoDTO)
                .collect(Collectors.toList());
    }
    public EmpleadoDTO guardarNuevoEmpleado(EmpleadoDTO empleadoDTO){
        if (Objects.nonNull(empleadoDTO.getId())) {
            throw new DevMountainError("El empleado ya existe");
        }
        empleadoDTO.setId(empleadoRepository
                .save(MapperUtil
                        .INSTANCE
                        .empleadoDTOToEmpleado(empleadoDTO))
                .getId());
        return empleadoDTO;
    }

    @Transactional
    public void actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoRepository
                .findById(empleadoDTO.getId())
                .orElseThrow(() -> new DevMountainError(
                        String.format("No se encontró un empleado con ID %s para actualizar.", empleadoDTO.getId())
                ));
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setEmail(empleado.getEmail());
        empleado.setRol(empleadoDTO.getRol());
        empleado.setEmpresa(MapperUtil.INSTANCE.empresaDTOTOEmpresa(empleadoDTO.getEmpresa()));
    }


    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
        return this.empleadoRepository.findById(id).isPresent();
    }

}
