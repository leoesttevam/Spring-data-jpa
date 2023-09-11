package br.com.alura.Spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.Funcionario;
import br.com.alura.Spring.data.repository.FuncionarioRepository;
import br.com.alura.Spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository repository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner sc) {
		System.out.println("Digite o nome");
		String nome = sc.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf");
		String cpf = sc.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario");
		BigDecimal salario = sc.nextBigDecimal();
		
		if(salario == new BigDecimal(0)) {
			salario = null;
		}
		
		System.out.println("Digite a data de contratação");
		String data = sc.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = repository.findAll(Specification
				.where(
						SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.CPF(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						
				);
		
		funcionarios.forEach(System.out::println);
	}
}
