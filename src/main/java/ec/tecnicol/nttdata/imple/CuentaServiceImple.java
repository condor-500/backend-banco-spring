package ec.tecnicol.nttdata.imple;

import ec.tecnicol.nttdata.dto.CuentaDTO;
import ec.tecnicol.nttdata.repository.CuentaRepository;
import ec.tecnicol.nttdata.service.CuentaService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImple implements CuentaService {

    @Autowired
    @Lazy
    private SessionFactory sessionFactory;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaDTO> allFind() {
        return (List<CuentaDTO>) cuentaRepository.findAll();
    }

    @Override
    public Optional<CuentaDTO> idFind(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public CuentaDTO save(CuentaDTO cuentaDTO) {
        return cuentaRepository.save(cuentaDTO);
    }

    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public Optional<CuentaDTO> porNumeroCuenta(Integer numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }


}
