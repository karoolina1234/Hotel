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

import domain.Quarto;
import dto.QuartoDto;
import service.QuartoService;

@RestController
@RequestMapping(value = "/quartos")
public class QuartoController {
	
	private QuartoService quartoService;

	public QuartoController(QuartoService quartoService) {
		this.quartoService = quartoService;
	}

	@GetMapping
	public List<QuartoDto> getAllQuartos() {
		return quartoService.findAll();
	}

	@GetMapping(value = "/{id}")
	public QuartoDto findQuartoById(@PathVariable("id") Integer id) {
		return quartoService.findById(id);
	}

	@PostMapping
	public 	Quarto saveQuarto(@RequestBody @Valid QuartoDto quartoDto) {
		return quartoService.save(quartoDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<QuartoDto> editQuarto(@PathVariable("id") String id, @RequestBody @Valid QuartoDto quartoDto) {
		this.quartoService.edit(quartoDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Integer id) {
		this.quartoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
