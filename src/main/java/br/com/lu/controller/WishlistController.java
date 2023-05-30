package br.com.lu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lu.dto.PostWishlistDTO;
import br.com.lu.dto.WishlistDTO;
import br.com.lu.service.WishlistService;
import br.com.lu.validation.WishlistValidationException;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@GetMapping
	public List<WishlistDTO> getAllWishlists() {
		return wishlistService.findAll();
	}

	@PostMapping
	public ResponseEntity<String> createWishlist(@RequestBody PostWishlistDTO postWishlistDTO) {
		try {
			wishlistService.createOrUpdate(postWishlistDTO);
			return ResponseEntity.ok("Wishlist cadastrada com sucesso");
		} catch (WishlistValidationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteWishlist(@PathVariable String id) {
		wishlistService.remove(id);
	}

}
