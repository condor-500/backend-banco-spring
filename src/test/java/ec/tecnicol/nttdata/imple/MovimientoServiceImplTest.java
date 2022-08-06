package ec.tecnicol.nttdata.imple;

import ec.tecnicol.nttdata.dto.MovimientoDTO;
import ec.tecnicol.nttdata.repository.MovimientoRepository;
import ec.tecnicol.nttdata.service.MovimientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovimientoServiceImplTest {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    void findByFechaMovimientoBetween() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = formato.parse("2022-08-02");
        Date fecha2 = formato.parse("2022-08-05");
        System.out.println("holas");

   try {
       List<MovimientoDTO> movi = (List<MovimientoDTO>) movimientoService.findByFechaMovimientoBetween(fecha1, fecha2) ;

       movi.forEach( (p) ->{
           System.out.println(p.getNumeroCuenta()+" - "+ p.getFechaMovimiento()+" - "  + p.getValor() );
       } );
   } catch (Exception e) {
       System.out.println(e.getMessage());
   }




    }
}