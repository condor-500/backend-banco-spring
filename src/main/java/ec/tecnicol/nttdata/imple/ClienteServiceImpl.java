package ec.tecnicol.nttdata.imple;

import ec.tecnicol.nttdata.dto.ClienteDTO;
import ec.tecnicol.nttdata.repository.ClienteRepository;
import ec.tecnicol.nttdata.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> allFind() {
        return (List<ClienteDTO>) clienteRepository.findAll();
    }

    @Override
    public Optional<ClienteDTO> idFind(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return  clienteRepository.save(clienteDTO);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> buscarCedula(String cedula) {
        return clienteRepository.findByIdentificacion(cedula);
    }
}
