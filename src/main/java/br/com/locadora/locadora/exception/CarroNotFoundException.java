package br.com.locadora.locadora.exception;

public class CarroNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public CarroNotFoundException(String  mensagem) {
        super(mensagem);
    }
}
