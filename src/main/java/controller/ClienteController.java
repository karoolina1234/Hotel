package controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Cliente;
import dto.ClienteDto;

import service.ClienteService;



@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public List<ClienteDto> getAllClientes(){
		return clienteService.findAll();
	}
	
		
	@GetMapping(value = "/{id}")
	public ClienteDto findClienteById(@PathVariable("id") Integer id) {
		return clienteService.findById(id);
	}
	
	@PostMapping
	public Cliente saveCliente(@RequestBody @Valid ClienteDto clienteDto) {
		return clienteService.save(clienteDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> editCliente(@PathVariable("id") String id, @RequestBody @Valid ClienteDto clienteDto){
		this.clienteService.edit(clienteDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id) {
		this.clienteService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
