package ec.tecnicol.nttdata.controllers;

import ec.tecnicol.nttdata.dto.ClienteDTO;
import ec.tecnicol.nttdata.dto.CuentaDTO;
import ec.tecnicol.nttdata.dto.MovimientoDTO;
import ec.tecnicol.nttdata.service.ClienteService;
import ec.tecnicol.nttdata.service.CuentaService;
import ec.tecnicol.nttdata.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api")
public class BancoControlles {

    Logger log = LoggerFactory.getLogger(BancoControlles.class);

    @Autowired
    private ClienteService clienteService ;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService ;

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> findAllCientes(){
        return ResponseEntity.ok(clienteService.allFind());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> findId (@PathVariable Long id){
        Optional<ClienteDTO> cuenta = clienteService.idFind(id);
        if(cuenta.isPresent()){
            return ResponseEntity.ok(cuenta.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/clientes/")
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteDTO clienteDTO , BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<ClienteDTO> clienteDTO1 = clienteService.buscarCedula(clienteDTO.getIdentificacion());
        if(clienteDTO1.isPresent()){
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("MENSAJE","USUARIO REGISTRADO"));
        }

        ClienteDTO cliente= clienteService.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> edit ( @Valid @RequestBody ClienteDTO clienteDTO, BindingResult result ,@PathVariable Long id ) {
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<ClienteDTO> clienteDTO1 = clienteService.idFind(id) ;
        if(clienteDTO1.isPresent()){
            ClienteDTO  clienteDTO2= clienteDTO1.get();
            clienteDTO2.setNombre(clienteDTO.getNombre() );
            clienteDTO2.setDireccion(clienteDTO.getDireccion());
            clienteDTO2.setContrasenia(clienteDTO.getContrasenia());
            clienteDTO2.setEstado(clienteDTO.isEstado());
            clienteDTO2.setEdad(clienteDTO.getEdad());
            clienteDTO2.setGenero(clienteDTO.getGenero());
            clienteDTO2.setTelefono(clienteDTO.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDTO2) );
        }

         return ResponseEntity.badRequest()
                   .body(Collections.singletonMap("MENSAJE","CLIENTE NO ACTUALIZADO"));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente (@PathVariable Long id){
        Optional<ClienteDTO> clienteOp = clienteService.idFind(id);
        if(clienteOp.isPresent()){
            clienteService.delete(clienteOp.get().getIdCliente());
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }


    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo "+ err.getField() + " "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


    @GetMapping("/cuentas")
    public ResponseEntity<List<CuentaDTO>> findAllCuentas(){
        return ResponseEntity.ok(cuentaService.allFind());
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<CuentaDTO> findIdCuenta (@PathVariable Long id){
        Optional<CuentaDTO> cuenta = cuentaService.idFind(id);
        if(cuenta.isPresent()){
            return ResponseEntity.ok(cuenta.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cuentas/")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody CuentaDTO cuentaDTO , BindingResult result) {
        if(result.hasErrors()){
            return validar(result);
        }
        CuentaDTO cuentaDB= cuentaService.save(cuentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaDB);
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<?> editCuenta ( @Valid @RequestBody CuentaDTO cuentaDTO, BindingResult result ,@PathVariable Long id ) {
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<CuentaDTO> cuentaDTO1 = cuentaService.idFind(id) ;
        if(cuentaDTO1.isPresent()){
            CuentaDTO  cuentaDTO2= cuentaDTO1.get();
            cuentaDTO2.setTipoCuenta( cuentaDTO.getTipoCuenta());
            cuentaDTO2.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(cuentaDTO2) );
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> deleteCuenta (@PathVariable Long id){
        Optional<CuentaDTO> cuentaOP = cuentaService.idFind(id);
        if(cuentaOP.isPresent()){
            cuentaService.delete(cuentaOP.get().getIdClienteCueta());
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoDTO>> findAllMovimiento(){
        return ResponseEntity.ok(movimientoService.allFind());
    }

    @PostMapping("/movimientos/")
    public ResponseEntity<?> crearMovimientos(@Valid @RequestBody MovimientoDTO movimientoDTO , BindingResult result) {
       Optional<CuentaDTO> cuentaDTO1 = cuentaService.porNumeroCuenta(movimientoDTO.getNumeroCuenta())  ;
       if (cuentaDTO1.isPresent()){
       if(cuentaDTO1.get().getSaldoInicial() > 0 ){
           if(movimientoDTO.getTipoMovimiento().equals("CRE")){
              CuentaDTO  cuentaDTO02 = cuentaDTO1.get();
              cuentaDTO02.setNumeroCuenta(cuentaDTO1.get().getNumeroCuenta());
              cuentaDTO02.setTipoCuenta(cuentaDTO1.get().getTipoCuenta());
              cuentaDTO02.setSaldoInicial(cuentaDTO1.get().getSaldoInicial()  + movimientoDTO.getValor()  );
              cuentaDTO02.setEstado(cuentaDTO1.get().isEstado());
              cuentaDTO02.setIdClienteCueta(cuentaDTO1.get().getIdClienteCueta());
              cuentaService.save(cuentaDTO02);
              MovimientoDTO movimientoDTO1 = movimientoDTO  ;
              movimientoDTO1.setFechaMovimiento( new Date());
              movimientoDTO1.setSaldo( cuentaDTO1.get().getSaldoInicial()  + movimientoDTO.getValor()  );
              movimientoService.save(movimientoDTO1) ;
           }
           else if (movimientoDTO.getTipoMovimiento().equals("DEB")) {
               float valor = cuentaDTO1.get().getSaldoInicial() - movimientoDTO.getValor();
               CuentaDTO  cuentaDTO02 = cuentaDTO1.get();
               cuentaDTO02.setNumeroCuenta(cuentaDTO1.get().getNumeroCuenta());
               cuentaDTO02.setTipoCuenta(cuentaDTO1.get().getTipoCuenta());
               cuentaDTO02.setSaldoInicial(valor );
               cuentaDTO02.setEstado(cuentaDTO1.get().isEstado());
               cuentaDTO02.setIdClienteCueta(cuentaDTO1.get().getIdClienteCueta());
               cuentaService.save(cuentaDTO02);
               MovimientoDTO movimientoDTO1 = movimientoDTO  ;
               movimientoDTO1.setFechaMovimiento( new Date());
               movimientoDTO1.setSaldo( valor);
               movimientoService.save(movimientoDTO1) ;
           }
       }else {
           return ResponseEntity.badRequest()
                   .body(Collections.singletonMap("MENSAJE","SALDO NO DISPONIBLE"));
       }

       } else {
           return ResponseEntity.badRequest()
                   .body(Collections.singletonMap("MENSAJE","CUENTANO EXISTENTE"));
       }

        if(result.hasErrors()){
            return validar(result);
        }
       // MovimientoDTO movimientoDTO1 = movimientoService.save(movimientoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("MOVIMIENTO CREADO EXITO");
    }


    @GetMapping("/reportes")
    public ResponseEntity<?> buscarFecha(@RequestParam("fecha1") String fechaInicio, @RequestParam("fecha2") String fechaFin ) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = formato.parse(fechaInicio);
        Date fecha2 = formato.parse(fechaFin);

        List<MovimientoDTO> movimientoDTOS = movimientoService.findByFechaMovimientoBetween(fecha1, fecha2);
        return ResponseEntity.ok(movimientoDTOS);
    }

}
