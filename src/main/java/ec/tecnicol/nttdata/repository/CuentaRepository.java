package ec.tecnicol.nttdata.repository;

import ec.tecnicol.nttdata.dto.CuentaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends CrudRepository<CuentaDTO, Long> {

    Optional<CuentaDTO> findByNumeroCuenta(Integer numeroCuenta);





}
