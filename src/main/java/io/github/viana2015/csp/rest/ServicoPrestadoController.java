package io.github.viana2015.csp.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.viana2015.csp.model.entity.Cliente;
import io.github.viana2015.csp.model.entity.ServicoPrestado;
import io.github.viana2015.csp.model.repository.ClienteRepository;
import io.github.viana2015.csp.model.repository.ServicoPrestadoRepository;
import io.github.viana2015.csp.rest.dto.ServicoPrestadoDTO;
import io.github.viana2015.csp.util.BigDecimalConverter;


@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoPrestadoRepository servicoPrestadoRepository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Long idCliente =  dto.getIdCliente();
		
		Cliente cliente = 
				clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexsitente."));
		
		ServicoPrestado servicoPrestado =  new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome, 
			@RequestParam(value = "mes", required = false) Integer mes
		){
			return servicoPrestadoRepository.findByNomeClienteAndMes( "%" + nome + "%", mes);
	}

}
