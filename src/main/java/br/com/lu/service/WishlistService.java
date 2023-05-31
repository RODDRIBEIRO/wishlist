package br.com.lu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lu.dto.PostWishlistDTO;
import br.com.lu.dto.ProdutoDTO;
import br.com.lu.dto.WishlistDTO;
import br.com.lu.model.Produto;
import br.com.lu.model.Wishlist;
import br.com.lu.repository.ClienteRepository;
import br.com.lu.repository.ProdutoRepository;
import br.com.lu.repository.WishlistRepository;
import br.com.lu.validation.WishlistValidationException;

@Service
public class WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Optional<Wishlist> findById(String id) {
		return wishlistRepository.findById(id);
	}

	public List<WishlistDTO> findAll() {
		return wishlistRepository.findAll().stream().map(this::toWishlistDTO).toList();
	}

	public Wishlist createOrUpdate(PostWishlistDTO postWishlistDTO) {
		return this.wishlistRepository.save(this.toWishlist(beforeSave(postWishlistDTO)));
	}

	public WishlistDTO beforeSave(PostWishlistDTO postWishlistDTO) {
		List<ProdutoDTO> produtoDTOs = new ArrayList<>();
		WishlistDTO wishlistDTO = new WishlistDTO();

		// validação verifica se o Produto que será adicionado existe na base de dados
		if (!produtoRepository.existsById(postWishlistDTO.getProduto().getId())) {
			throw new WishlistValidationException("Produto não cadastrado");
		}

		// validação verifica se o Cliente que será adicionado existe na base de dados
		if (!clienteRepository.existsById(postWishlistDTO.getCliente().getId())) {
			throw new WishlistValidationException("Cliente não cadastrado");
		}

		if (this.wishlistRepository.existsByClienteId(postWishlistDTO.getCliente().getId())) {
			wishlistDTO = this
					.toWishlistDTO(this.wishlistRepository.findByClienteId(postWishlistDTO.getCliente().getId()).get());

			// validação verifica se o Produto que será adicionado existe na lista, caso
			// exista, dispara exceção.
			if (wishlistDTO.getProdutos().stream()
					.anyMatch(produto -> produto.getGtin().equals(postWishlistDTO.getProduto().getGtin()))) {
				throw new WishlistValidationException(
						"Produto ja existe na Wishlist e não pode ser adicionado novamente");
			}

			// validação verifica tamanho da lista se maior que 20 dispara exceção
			if (wishlistDTO.getProdutos().size() <= 20) {
				produtoDTOs.addAll(wishlistDTO.getProdutos());
				produtoDTOs.add(postWishlistDTO.getProduto());
				wishlistDTO.setProdutos(produtoDTOs);
			} else {
				throw new WishlistValidationException("Cada Cliente pode ter no maximo 20 Produtos na Wishlist");
			}
		} else {
			wishlistDTO.setId(postWishlistDTO.getId());
			wishlistDTO.setCliente(postWishlistDTO.getCliente());
			produtoDTOs.add(postWishlistDTO.getProduto());
			wishlistDTO.setProdutos(produtoDTOs);
		}

		return wishlistDTO;
	}

	public void removeProduto(String wishlistId, String produtoId) {
		Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);

		if (wishlist != null) {
			List<Produto> produtos = wishlist.getProdutos();
			produtos.removeIf(produto -> produto.getId().equals(produtoId));

			wishlistRepository.save(wishlist);
		} else {
			throw new WishlistValidationException("Registro não encontrado");
		}
	}

	private WishlistDTO toWishlistDTO(Wishlist wishlist) {
		return modelMapper.map(wishlist, WishlistDTO.class);
	}

	private Wishlist toWishlist(WishlistDTO wishlistDTO) {
		return modelMapper.map(wishlistDTO, Wishlist.class);
	}

}
