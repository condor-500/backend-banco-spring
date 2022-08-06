package ec.tecnicol.nttdata.service;


import ec.tecnicol.nttdata.dto.CuentaDTO;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<CuentaDTO> allFind();
    Optional<CuentaDTO> idFind(Long id);
    CuentaDTO save (CuentaDTO cuentaDTO);
    void delete ( Long id);
    Optional<CuentaDTO> porNumeroCuenta(Integer numeroCuenta);
}
