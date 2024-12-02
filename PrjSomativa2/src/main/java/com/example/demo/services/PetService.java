package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Pet;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.PetRepository;


@Service
public class PetService {
	private final PetRepository petrepository;
    private final ClienteRepository clienterepository;
	
	@Autowired
	public PetService(PetRepository petrepository, ClienteRepository clienterepository) {
		this.petrepository = petrepository;
		this.clienterepository = clienterepository;
	}

	public Pet salvarPet(Pet pet) {
		if(pet.getCliente() !=null && pet.getCliente().getId() !=null ) {
			Optional<Cliente> cliente = clienterepository.findById(pet.getCliente().getId()); 
			if(cliente.isPresent()) {
				pet.setCliente(cliente.get());
				return petrepository.save(pet);
			} else {
				throw new RuntimeException("Cliente não encontrado");  
			}
		} else {
			throw new RuntimeException("ID do Cliente é obrigatório");
		}
	}

	public Pet buscarPetPorId(Long id) {
		return petrepository.findById(id).orElse(null);
	}

	public List<Pet> buscarTodosPet() {
		return petrepository.findAll();
	}

	public void excluirPet(Long id) {
		petrepository.deleteById(id);
	}

	public Pet atualizarPet(Long id, Pet petatualizado) {
		Optional<Pet> petexistente = petrepository.findById(id);
		if (petexistente.isPresent()) {
			Pet pet = petexistente.get();
			pet.setNome(petatualizado.getNome());
			pet.setTipo(petatualizado.getTipo());
			pet.setRaca(petatualizado.getRaca());
			pet.setIdade(petatualizado.getIdade());
			if(petatualizado.getCliente() !=null) {
				pet.setCliente(petatualizado.getCliente());
			}
			return petrepository.save(pet);
		} else
			return null;
	}
}
