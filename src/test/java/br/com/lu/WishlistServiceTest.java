package br.com.lu;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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
		
	}

}
