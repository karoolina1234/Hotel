package service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Cliente;

import dto.ClienteDto;

import repository.ClienteRepository;

public class ClienteService {

	private ClienteRepository clienteRepository;
	
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	public Cliente save(ClienteDto clienteDto) {
		Integer id = clienteDto.getId();
		String nome = clienteDto.getNome();
		String email = clienteDto.getEmail();
		String senha = clienteDto.getSenha();
		Cliente clienteSalvar = new Cliente(id, nome, email, senha);
		Cliente cliente = this.clienteRepository.save(clienteSalvar);
		return cliente;
	}
	
	
	public Cliente findById(int id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		throw new ServiceException("Cliente não foi encontrado!");
	}
	
	public List<ClienteDto> findAll() {
		List<ClienteDto> clienteRetorno = new ArrayList<ClienteDto>();
		Iterable<Cliente> clientes = clienteRepository.findAll();

		for (Cliente cliente  : clientes) {
			ClienteDto clienteDto = criarClienteDto(cliente);
		
			clienteRetorno.add(clienteDto);
		}

		return clienteRetorno;
	}
	
	private ClienteDto criarClienteDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setSenha(cliente.getSenha());
		return clienteDto;
	}
	
	public void delete(Integer id) {
		this.clienteRepository.deleteById(id);
	}

	public void edit(ClienteDto clienteDto) {
		Optional<Cliente> clienteAtualizar = clienteRepository.findById(clienteDto.getId());
		if (clienteAtualizar.isPresent()) {
			Integer id = clienteDto.getId();
			String nome = clienteDto.getNome();
			String email = clienteDto.getEmail();
			String senha = clienteDto.getSenha();
			
		clienteRepository.editById(id, nome, email, senha);
		}
		throw new ServiceException("Cliente não encontrado");
	}


	public ClienteDto findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			ClienteDto clienteDto = criarClienteDto(cliente.get());
			return clienteDto;
		}
		throw new ServiceException("Cliente não encontrados");
	}


}
