package com.devmountain.ingresosegresos.movimiento;

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

@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
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
            throw new DevMountainError("No se envió el movimiento a registrar.");
        } else if (Objects.nonNull(movimientoDTO.getId())) {
            throw new DevMountainError(String.format("El movimiento con ID %s ya se encuentra registrado.", movimientoDTO.getId()));
        }
        Movimiento movimiento = MapperUtil.INSTANCE.movimientoDTOTOMovimiento(movimientoDTO);
        movimiento = movimientoRepository.save(movimiento);
        movimientoDTO.setId(movimiento.getId());
        return movimientoDTO;
    }

    @Transactional
    public void actualizarMovimiento(MovimientoDTO movimientoDTO) {
        if (Objects.isNull(movimientoDTO.getId())) {
            throw new DevMountainError("El ID del movimiento a actualizar no puede ser un valor nulo.");
        }

        Movimiento movimiento = movimientoRepository
                .findById(movimientoDTO.getId())
                .orElseThrow(() -> new DevMountainError(
                        String.format("No se encontró un movimiento con ID %s para actualizar.", movimientoDTO.getId())
                ));
        movimiento.setMonto(movimientoDTO.getMonto());
        movimiento.setConcepto(movimientoDTO.getConcepto());
        movimiento.setEmpleado(MapperUtil.INSTANCE.empleadoDTOToEmpleado(movimientoDTO.getEmpleado()));
    }

    public void eliminarMovimiento(Integer idMovimiento) {
        Movimiento movimiento = movimientoRepository
                .findById(idMovimiento)
                .orElseThrow(() -> new DevMountainError(
                        String.format("No se encontró un movimiento con ID %s para ser eliminado.", idMovimiento)
                ));
        movimientoRepository.delete(movimiento);
    }

    public List<MovimientoDTO> listarMovimientosEmpresa(Integer idEmpresa) {

        if (Objects.isNull(idEmpresa)) {
            throw new DevMountainError("ID de empresa inválido.");
        }

        Empresa empresa = empresaRepository
                .findById(idEmpresa)
                .orElseThrow(() -> new DevMountainError(
                        String.format("No se encontró una empresa con ID %s", idEmpresa)
                ));

        List<Movimiento> movimientos = new ArrayList<>();
        empresa.getEmpleados().forEach(x -> movimientos.addAll(x.getMovimientos()));
        return movimientos
                .stream()
                .map(MapperUtil.INSTANCE::movimientoToMovimientoDTO)
                .collect(Collectors.toList());
    }
}
