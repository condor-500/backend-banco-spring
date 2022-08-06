package ec.tecnicol.nttdata.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@ToString
public class MovimientoDTO implements Serializable {


    private static final long serialVersionUID = 992166730665426893L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimientos;
    @Temporal(TemporalType.DATE)
    private Date fechaMovimiento;
    private String tipoMovimiento;
    private float valor ;
    private float saldo ;
    private Integer numeroCuenta;




}
