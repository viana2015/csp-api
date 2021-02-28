package io.github.viana2015.csp.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class ServicoPrestado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 180)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente; 
	
	private BigDecimal valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
}
