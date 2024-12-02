package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienterepository;

	@Autowired
	public ClienteService(ClienteRepository clienterepository) {
		this.clienterepository = clienterepository;
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienterepository.save(cliente);
	}

	public Cliente buscarClientePorId(Long id) {
		return clienterepository.findById(id).orElse(null);
	}

	public List<Cliente> buscarTodosClientes() {
		return clienterepository.findAll();
	}

	public void excluirCliente(Long id) {
		clienterepository.deleteById(id);
	}

	public Cliente atualizarCliente(Long id, Cliente clienteatualizado) {
		Optional<Cliente> clienteexistente = clienterepository.findById(id);
		if (clienteexistente.isPresent()) {
			Cliente cliente = clienteexistente.get();
			cliente.setNome(clienteatualizado.getNome());
			cliente.setTelefone(clienteatualizado.getTelefone());
			cliente.setEmail(clienteatualizado.getEmail());
			return clienterepository.save(cliente);
		} else {
			return null;
		}
	}
}


