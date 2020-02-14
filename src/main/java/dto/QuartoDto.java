package dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import domain.QuartoTipo;

public class QuartoDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(max = 4, message = "Numero de quarto pode ter no maximo 4 numeros")
	@Size(min = 3, message = "Quarto precisa ter no minimo um numero")
	private int numero;
	
	@NotNull
	@NotEmpty
	private QuartoTipo tipo;
	
	@NotNull
	@NotEmpty
	private double valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public QuartoTipo getTipo() {
		return tipo;
	}

	public void setTipo(QuartoTipo tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


}
