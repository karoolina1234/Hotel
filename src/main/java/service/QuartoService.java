package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Quarto;
import domain.QuartoTipo;
import dto.QuartoDto;
import repository.QuartoRepository;

public class QuartoService {
	

	private QuartoRepository quartoRepository;

	
	public QuartoService(QuartoRepository quartoRepository) {
		this.quartoRepository = quartoRepository;
	}

	public Quarto save(QuartoDto quartoDto) {
		int numero = quartoDto.getNumero();
		double valor = quartoDto.getValor();
		QuartoTipo tipo = quartoDto.getTipo();

		Quarto quartoParaSalvar = new Quarto(numero, tipo, valor);
		Quarto quarto = this.quartoRepository.save(quartoParaSalvar);
		return quarto;
	}

	public Quarto findByNumero(int numero) {
		Optional<Quarto> quarto = quartoRepository.findByNumero(numero);
		if (quarto.isPresent()) {
			return quarto.get();
		}
		throw new ServiceException("Quarto não encontrado");
	}

	public List<QuartoDto> findAll() {
		List<QuartoDto> quartoParaRetorno = new ArrayList<QuartoDto>();
		Iterable<Quarto> quartos = quartoRepository.findAll();

		for (Quarto quarto : quartos) {
			QuartoDto quartoDto = criarQuartoDto(quarto);
		
			quartoParaRetorno.add(quartoDto);
		}

		return quartoParaRetorno;
	}

	private QuartoDto criarQuartoDto(Quarto quarto) {
		QuartoDto quartoDto = new QuartoDto();
		quartoDto.setId(quarto.getId());
		quartoDto.setNumero(quarto.getNumero());
		quartoDto.setTipo(quarto.getTipo());
		quartoDto.setValor(quarto.getValor());
		return quartoDto;
	}

	public void delete(Integer id) {
		this.quartoRepository.deleteById(id);
	}

	public void edit(QuartoDto quartoDto) {
		Optional<Quarto> QuartoParaAtualizar = quartoRepository.findById(quartoDto.getId());
		if (QuartoParaAtualizar.isPresent()) {
			Integer id = quartoDto.getId();
			int numero = quartoDto.getNumero();
			double valor = quartoDto.getValor();
			QuartoTipo tipo = quartoDto.getTipo();
			
			quartoRepository.editById(id, numero, valor, tipo);
		}
		throw new ServiceException("Quarto não encontrado");
	}


	public QuartoDto findById(Integer id) {
		Optional<Quarto> quarto = quartoRepository.findById(id);
		if (quarto.isPresent()) {
			QuartoDto quartoDto = criarQuartoDto(quarto.get());
			return quartoDto;
		}
		throw new ServiceException("Quarto não encontrados");
	}

}
