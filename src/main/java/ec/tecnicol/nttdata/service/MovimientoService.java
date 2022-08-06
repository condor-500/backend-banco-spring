package ec.tecnicol.nttdata.service;

import ec.tecnicol.nttdata.dto.CuentaDTO;
import ec.tecnicol.nttdata.dto.MovimientoDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    List<MovimientoDTO> allFind();
    Optional<MovimientoDTO> idFind(Long id);
    MovimientoDTO save (MovimientoDTO movimientoDTO);
    void delete ( Long id);
    List<MovimientoDTO> findByFechaMovimientoBetween(Date starDate, Date endDate);
}
