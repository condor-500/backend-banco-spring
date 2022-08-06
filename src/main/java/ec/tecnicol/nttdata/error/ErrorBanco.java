package ec.tecnicol.nttdata.error;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorBanco extends  Exception {

    public String error;
    public String mesaggeError;




}
