package ec.tecnicol.nttdata.repository;

import ec.tecnicol.nttdata.dto.MovimientoDTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends CrudRepository<MovimientoDTO, Long> {
    @Query("select m from MovimientoDTO m where m.fechaMovimiento between ?1 and ?2")
    List<MovimientoDTO> findByFechaMovimientoBetween(@Nullable Date fechaMovimientoStart, @Nullable Date fechaMovimientoEnd);





}
