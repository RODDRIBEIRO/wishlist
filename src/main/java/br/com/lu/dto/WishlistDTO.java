package br.com.lu.dto;

import java.util.List;

import lombok.Data;

@Data
public class WishlistDTO {
	private String id;
	private ClienteDTO cliente;
	private List<ProdutoDTO> produtos;
}
