package repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Integer>  {
	

	@Query("SELECT f.id from funcionarios q WHERE id = :id")
	Optional<Cliente> findById(@Param("id") Integer id);
	
	@Query("UPDATE cliente set   id = :id, nome = :nome, email = :email, senha = :senha")
	void editById(@Param("id") Integer id, @Param("nome") String nome, @Param("email") String email,
			@Param("senha") String senha);
}
