package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Funcionario;
import dto.FuncionarioDto;
import repository.FuncionarioRepository;

public class FuncionarioService {

	private FuncionarioRepository funcionarioRepository;

	
	public FuncionarioService(FuncionarioRepository  funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public Funcionario save(FuncionarioDto funcionarioDto) {
		String nome = funcionarioDto.getNome();
		String email = funcionarioDto.getEmail();
		String senha = funcionarioDto.getSenha();

		Funcionario funcionarioParaSalvar = new Funcionario(nome, email, senha);
	    Funcionario funcionario = this.funcionarioRepository.save(funcionarioParaSalvar);
		return funcionario;
	}

	public Funcionario findById(int id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			return funcionario.get();
		}
		throw new ServiceException("funcionario não encontrado");
	}

	public List<FuncionarioDto> findAll() {
		List<FuncionarioDto> funcionarioParaRetorno = new ArrayList<FuncionarioDto>();
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();

		for (Funcionario funcionario : funcionarios) {
			FuncionarioDto funcionarioDto = criarFuncionarioDto(funcionario);
		
			funcionarioParaRetorno.add(funcionarioDto);
		}

		return funcionarioParaRetorno;
	}

	private FuncionarioDto criarFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setNome(funcionario.getNome());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setSenha(funcionario.getSenha());
		return funcionarioDto;
	}

	public void delete(Integer id) {
		this.funcionarioRepository.deleteById(id);
	}

	public void edit(FuncionarioDto funcionarioDto) {
		Optional<Funcionario> funcionarioParaAtualizar = funcionarioRepository.findById(funcionarioDto.getId());
		if (funcionarioParaAtualizar.isPresent()) {
			Integer id = funcionarioDto.getId();
			String nome = funcionarioDto.getNome();
			String email = funcionarioDto.getEmail();
			String senha = funcionarioDto.getSenha();
			
			funcionarioRepository.editById(id, nome, email, senha);
		}
		throw new ServiceException("funcionario não encontrado");
	}


	public FuncionarioDto findById(Integer id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			FuncionarioDto funcionarioDto = criarFuncionarioDto(funcionario.get());
			return funcionarioDto;
		}
		throw new ServiceException("funcionario não encontrados");
	}

}
