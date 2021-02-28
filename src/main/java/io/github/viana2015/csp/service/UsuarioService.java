package io.github.viana2015.csp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.viana2015.csp.exception.UsuarioCadastradoException;
import io.github.viana2015.csp.model.entity.Usuario;
import io.github.viana2015.csp.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario usuario) {
		boolean exists = usuarioRepository.existsByUsername(usuario.getUsername());
		if (exists) {
			throw new UsuarioCadastradoException(usuario.getUsername());
		}
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository
							.findByUsername(username)
							.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}

}
