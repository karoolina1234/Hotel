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

import domain.Funcionario;
import dto.FuncionarioDto;
import service.FuncionarioService;



@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	
	
	private FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	} 

	@GetMapping
	public List<FuncionarioDto> getAllFuncionario() {
		return funcionarioService.findAll();
	}

	@GetMapping(value = "/{id}")
	public FuncionarioDto findFuncionarioById(@PathVariable("id") Integer id) {
		return funcionarioService.findById(id);
	}

	@PostMapping
	public 	Funcionario saveFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {
		return funcionarioService.save(funcionarioDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDto> editFuncionario(@PathVariable("id") String id, @RequestBody @Valid FuncionarioDto funcionarioDto) {
		this.funcionarioService.edit(funcionarioDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id) {
		this.funcionarioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
