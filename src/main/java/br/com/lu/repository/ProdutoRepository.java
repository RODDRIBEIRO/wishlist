package br.com.lu.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lu.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

	Optional<Produto> findById(String id);

	void deleteById(String id);
	
	boolean existsById(String id);
}
