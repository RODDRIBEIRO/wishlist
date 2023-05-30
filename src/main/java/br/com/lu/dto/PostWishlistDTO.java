package br.com.lu.dto;

import lombok.Data;

@Data
public class PostWishlistDTO {
	private String id;
	private ClienteDTO cliente;
	private ProdutoDTO produto;
}
