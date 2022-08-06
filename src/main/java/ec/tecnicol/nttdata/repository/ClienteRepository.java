package ec.tecnicol.nttdata.repository;

import ec.tecnicol.nttdata.dto.ClienteDTO;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<ClienteDTO, Long> {

    Optional<ClienteDTO> findByIdentificacion(String cedula);
}
