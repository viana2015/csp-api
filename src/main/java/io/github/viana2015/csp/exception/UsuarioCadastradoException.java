package io.github.viana2015.csp.exception;

public class UsuarioCadastradoException extends RuntimeException {
	
	public UsuarioCadastradoException(String login) {
		super("Usuário já cadastrado para o login " + login);
	}

}
