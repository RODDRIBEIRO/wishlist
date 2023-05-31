package br.com.lu.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lu.model.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

	Optional<Wishlist> findById(String id);

	Optional<Wishlist> findByClienteId(String id);

	boolean existsByClienteId(String id);
}
