package ec.tecnicol.nttdata.service;

import ec.tecnicol.nttdata.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<ClienteDTO> allFind();
    Optional<ClienteDTO> idFind(Long id);
    ClienteDTO save (ClienteDTO clienteDTO);
    void delete ( Long id);
    Optional<ClienteDTO> buscarCedula(String cedula);

}
