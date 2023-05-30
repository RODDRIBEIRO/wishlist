package br.com.lu.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lu.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

	Optional<Cliente> findById(String id);
	
	boolean existsById(String id);
	
	void deleteById(String id);

}
