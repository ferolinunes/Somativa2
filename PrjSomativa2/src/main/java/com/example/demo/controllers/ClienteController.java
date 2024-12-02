package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Cliente;
import com.example.demo.services.ClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
		private final ClienteService clienteservice;

		@Autowired
		public ClienteController(ClienteService clienteservice) {
			this.clienteservice = clienteservice;
		}

		@PostMapping("/criar")
		public Cliente criarCliente(@RequestBody Cliente cliente) {
			return clienteservice.salvarCliente(cliente);
		}

		@GetMapping
		public List<Cliente> buscarTodos() {
			return clienteservice.buscarTodosClientes();
			}

		@GetMapping("/{id}")
		public Cliente buscarPorId(@PathVariable Long id) {
			return clienteservice.buscarClientePorId(id);
		}

		@DeleteMapping("/{id}")
		public void deleteCliente(@PathVariable Long id) {
			clienteservice.excluirCliente(id);
		}

		@PutMapping
		public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
			Cliente clienteatualizado = clienteservice.atualizarCliente(id, cliente);
			if (clienteatualizado != null) {
				return ResponseEntity.ok(clienteatualizado);
			} else {
				return null;
			}
		}


	}
