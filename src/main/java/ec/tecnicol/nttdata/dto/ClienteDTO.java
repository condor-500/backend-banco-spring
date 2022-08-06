package ec.tecnicol.nttdata.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class ClienteDTO extends PersonaDTO  implements Serializable {

    private static final long serialVersionUID = 4118531315999780501L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String contrasenia;
    private boolean estado ;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

   public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
