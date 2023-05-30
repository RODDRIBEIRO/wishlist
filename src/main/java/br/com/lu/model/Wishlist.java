package br.com.lu.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "wishlists")
public class Wishlist {
	
	@Id
	private String id;
	@DBRef
	private Cliente cliente;
	@DBRef
	private List<Produto> produtos;
}
