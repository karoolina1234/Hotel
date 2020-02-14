package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.Quarto;
import domain.QuartoTipo;

public interface QuartoRepository extends CrudRepository<Quarto, Integer> {

	@Query("SELECT q.numero from quarto q WHERE numero = :numero")
	Optional<Quarto> findByNumero(@Param("numero") int numero);

	@Query("UPDATE Quarto set   id = :id, numero = :numero, valor = :valor, tipo = :tipo")
	void editById(@Param("id") Integer id, @Param("numero") int numero, @Param("valor") double valor,
			@Param("tipo") QuartoTipo tipo);

}
