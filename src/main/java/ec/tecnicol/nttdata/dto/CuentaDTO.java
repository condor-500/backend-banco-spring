package ec.tecnicol.nttdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Table (name = "cuenta")
@Getter
@Setter
@ToString
public class CuentaDTO  implements Serializable {


    private static final long serialVersionUID = 3398120702778661431L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta ;
    private Integer numeroCuenta;
    private String tipoCuenta;
    private float saldoInicial;
    private boolean estado;
    private Long idClienteCueta ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClienteCueta", referencedColumnName = "idCliente", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ClienteDTO clienteDToes;

}
