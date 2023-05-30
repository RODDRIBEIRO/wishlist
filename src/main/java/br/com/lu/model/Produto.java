package br.com.lu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "produtos")
public class Produto {

	@Id
	private String id;
	private String gtin;
	private String nome;
}
