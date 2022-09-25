package com.devmountain.ingresosegresos.movimiento;

import com.devmountain.ingresosegresos.empleado.EmpleadoRepository;
import com.devmountain.ingresosegresos.empresa.EmpresaRepository;
import com.devmountain.ingresosegresos.empresa.EmpresaService;
import com.devmountain.ingresosegresos.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;

    public List<MovimientoDTO> listarMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos
                .stream()
                .map(MapperUtil.INSTANCE::movimientoToMovimientoDTO)
                .collect(Collectors.toList());
    }

    public MovimientoDTO agregarMovimiento(MovimientoDTO movimientoDTO) {
        if (Objects.isNull(movimientoDTO)) {
            throw new IllegalArgumentException("No se envió el movimiento a registrar.");
        } else if (Objects.nonNull(movimientoDTO.getId())) {
            throw new IllegalArgumentException(String.format("El movimiento con ID %s ya se encuentra registrado.", movimientoDTO.getId()));
        }
        Movimiento movimiento = MapperUtil.INSTANCE.movimientoDTOTOMovimiento(movimientoDTO);
        movimiento = movimientoRepository.save(movimiento);
        movimientoDTO.setId(movimiento.getId());
        return movimientoDTO;
    }

    @Transactional
    public void actualizarMovimiento(MovimientoDTO movimientoDTO) {
        if (Objects.isNull(movimientoDTO.getId())) {
            throw new IllegalArgumentException("El ID del movimiento a actualizar no puede ser un valor nulo.");
        }

        Movimiento movimiento = movimientoRepository
                .findById(movimientoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No se encontró un movimiento con ID %s para actualizar.", movimientoDTO.getId())
                ));
        movimiento.setMonto(movimientoDTO.getMonto());
        movimiento.setConcepto(movimientoDTO.getConcepto());
        movimiento.setEmpleado(MapperUtil.INSTANCE.empleadoDTOToEmpleado(movimientoDTO.getEmpleado()));
    }

    public void eliminarMovimiento(Integer idMovimiento) {
        Movimiento movimiento = movimientoRepository
                .findById(idMovimiento)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No se encontró un movimiento con ID %s para ser eliminado.", idMovimiento)
                ));
        movimientoRepository.delete(movimiento);
    }

    public List<MovimientoDTO> consultarMovimientosEmpleado(Integer idEmpleado) {
        if (Objects.isNull(idEmpleado)) {
            throw new IllegalArgumentException("Error al consultar movimientos de un empleado, ID empleado nulo.");
        }

        if (!empleadoRepository.existsById(idEmpleado)) {
            throw new IllegalArgumentException(
                    String.format("No se encontró empleado registrado con ID %s", idEmpleado)
            );
        }

        List<Movimiento> movimientos = movimientoRepository.findByEmpleadoId(idEmpleado);

        if (movimientos.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("No se encontraron movimientos para el empleado con ID %s", idEmpleado)
            );
        }

        return movimientos
                .stream()
                .map(MapperUtil.INSTANCE::movimientoToMovimientoDTO)
                .collect(Collectors.toList());
    }

    public List<MovimientoDTO> consultarMovimientosEmpresa(Integer idEmpresa) {
        if (Objects.isNull(idEmpresa)) {
            throw new IllegalArgumentException("Error al consultar movimientos de una empresa, ID empresa nulo.");
        }

        if (!empresaRepository.existsById(idEmpresa)) {
            throw new IllegalArgumentException(
                    String.format("No se encontró una empresa registrada con el ID %s", idEmpresa)
            );
        }

        List<Movimiento> movimientos = movimientoRepository.findByEmpleadoEmpresaId(idEmpresa);

        if (movimientos.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("No se encontraron movimientos para la empresa con ID %s", idEmpresa)
            );
        }

        return movimientos
                .stream()
                .map(MapperUtil.INSTANCE::movimientoToMovimientoDTO)
                .collect(Collectors.toList());
    }
}
