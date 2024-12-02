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
import com.example.demo.entities.Pet;
import com.example.demo.services.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {
	private final PetService petservice;

	@Autowired
	public PetController(PetService petservice) {
		this.petservice = petservice;
	}

	@PostMapping("/criar")
	public Pet criarPet(@RequestBody Pet pet) {
		return petservice.salvarPet(pet);
	}

	@GetMapping
	public List<Pet> buscarTodos() {
		return petservice.buscarTodosPet();
		}

	@GetMapping("/{id}")
	public Pet buscarPorId(@PathVariable Long id) {
		return petservice.buscarPetPorId(id);
	}
	
}