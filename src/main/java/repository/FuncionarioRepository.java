package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Funcionario;


import org.springframework.data.repository.query.Param;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;


public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
	
	@Query("SELECT f.id from funcionarios q WHERE id = :id")
	Optional<Funcionario> findById(@Param("id") Integer id);

	@Query("UPDATE funcionario set   id = :id, nome = :nome, email = :email, senha = :senha")
	void editById(@Param("id") Integer id, @Param("nome") String nome, @Param("email") String email,
			@Param("senha") String senha);

}
