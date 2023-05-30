package br.com.lu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lu.model.Produto;
import br.com.lu.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	

}
