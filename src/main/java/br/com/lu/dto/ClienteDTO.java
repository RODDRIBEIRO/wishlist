package br.com.lu.dto;

import lombok.Data;

@Data
public class ClienteDTO {
	private String id;
	private String nome;
	private String documentoFiscal;
	private String email;
}
