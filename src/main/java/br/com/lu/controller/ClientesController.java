package br.com.lu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lu.model.Cliente;
import br.com.lu.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

}
