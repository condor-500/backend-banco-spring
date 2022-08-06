package ec.tecnicol.nttdata.imple;

import ec.tecnicol.nttdata.dto.MovimientoDTO;
import ec.tecnicol.nttdata.repository.MovimientoRepository;
import ec.tecnicol.nttdata.service.MovimientoService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    @Lazy
    private SessionFactory sessionFactory;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public List<MovimientoDTO> allFind() {
        return (List<MovimientoDTO>) movimientoRepository.findAll();
    }

    @Override
    public Optional<MovimientoDTO> idFind(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public MovimientoDTO save(MovimientoDTO movimientoDTO) {
        return movimientoRepository.save(movimientoDTO);
    }

    @Override
    public void delete(Long id) {
            movimientoRepository.deleteById(id);
    }

    @Override
    public List<MovimientoDTO> findByFechaMovimientoBetween(Date starDate, Date endDate) {


        return movimientoRepository.findByFechaMovimientoBetween(starDate,endDate);
    }


}
