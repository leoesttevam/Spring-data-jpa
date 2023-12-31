package br.com.alura.Spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.Spring.data.orm.Funcionario;
import br.com.alura.Spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

	List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);
	
	@Query(value = "SELECT * FROM Funcionarios f WHERE f.data_contratacao >= : data", nativeQuery = true)
	List<Funcionario> findDataContratacaomaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM Funcionario f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
