package br.com.lu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lu.dto.ClienteDTO;
import br.com.lu.dto.PostWishlistDTO;
import br.com.lu.dto.ProdutoDTO;
import br.com.lu.service.WishlistService;

@RunWith(SpringRunner.class)
public class WishlistServiceTest {
	
	@TestConfiguration
	static class WishlistServiceConfiguration{
		
		@Bean
		public WishlistService wishlistService() {
			return new WishlistService();
		}
	}
	
	@Autowired
	WishlistService wishlistService;
	
	@Test
	public void wishlishLimiteProdutosLista() {
		List<PostWishlistDTO> postWishlistDTOs = new ArrayList<>();

		IntStream.range(0, 21).forEach(i -> {
		    PostWishlistDTO postWishlistDTO = new PostWishlistDTO();

		    // Set the properties of ClienteDTO
		    ClienteDTO clienteDTO = new ClienteDTO();
		    clienteDTO.setId("clienteId" + i);
		    clienteDTO.setNome("Cliente" + i);
		    clienteDTO.setDocumentoFiscal("DocumentoFiscal" + i);
		    clienteDTO.setEmail("cliente" + i + "@example.com");
		    postWishlistDTO.setCliente(clienteDTO);

		    // Set the properties of ProdutoDTO
		    ProdutoDTO produtoDTO = new ProdutoDTO();
		    produtoDTO.setId("produtoId" + i);
		    produtoDTO.setGtin("Gtin" + i);
		    produtoDTO.setNome("Produto" + i);
		    postWishlistDTO.setProduto(produtoDTO);

		    // Add the PostWishlistDTO to the list
		    postWishlistDTOs.add(postWishlistDTO);
		    
		    assertEquals(wishlistService.createOrUpdate(postWishlistDTO), null) ;
		});
		
		
		
	}
	
}
