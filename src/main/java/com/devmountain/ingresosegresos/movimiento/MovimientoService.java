package com.devmountain.ingresosegresos.movimiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovimientoService {
    @Autowired
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper mapper;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
        mapper = MovimientoMapper.INSTANCE;
    }

    public List<MovimientoDTO> listarMovimientos() {
        return movimientoRepository.findAll()
                .stream()
                .map(mapper::movimientoToMovimientoDTO)
                .collect(Collectors.toList());
    }

    public MovimientoDTO agregarMovimiento(MovimientoDTO movimientoDTO) {
        if (Objects.isNull(movimientoDTO)) {
            throw new IllegalArgumentException("No se envió el movimiento a registrar.");
        } else if (Objects.nonNull(movimientoDTO.getId())) {
            throw new IllegalArgumentException(String.format("El movimiento con ID %s ya se encuentra registrado.", movimientoDTO.getId()));
        }
        Movimiento movimiento = mapper.movimientoDTOTOMovimiento(movimientoDTO);
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
        movimiento.setEmpleado(movimientoDTO.getEmpleado());
    }

    public void eliminarMovimiento(Integer idMovimiento) {
        Movimiento movimiento = movimientoRepository
                .findById(idMovimiento)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No se encontró un movimiento con ID %s para ser eliminado.", idMovimiento)
                ));
        movimientoRepository.delete(movimiento);
    }
}
