package br.com.alura.Spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.Funcionario;
import br.com.alura.Spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	private Boolean system = true;
	
	private final FuncionarioRepository repository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Buscar funcionario pelo nome, data contratação e salario");
			System.out.println("3 - Busca funcionario pela data contratação");
			
			int action = sc.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(sc);
				break;
			case 2:
				buscaFuncionarioNomeSalarioData(sc);
				break;
			case 3:
				BuscaFuncionarioDataContratacao(sc);
				break;
			default:
				system = false;
				break;
			}
		}
		
	}
	
	private void buscaFuncionarioNome(Scanner sc) {
		System.out.println("Qual  nome deseja pesquisar");
		String nome = sc.next();
		
		List<Funcionario> list = repository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioData(Scanner sc) {
		System.out.println("Qual nome deseja pesquisar");
		String nome = sc.next();
		
		System.out.println("Qual data de contratação deseja pesquisar");
		String data = sc.next();
		LocalDate time = LocalDate.parse(data, formatter);
		
		System.out.println("Qual salario deseja pesquisar");
		BigDecimal salario = sc.nextBigDecimal();
		
		List<Funcionario> funcionarios = repository.findNomeSalarioMaiorDataContratacao(nome, salario, time);
		
		funcionarios.forEach(System.out::println);
	}
	
	private void BuscaFuncionarioDataContratacao(Scanner sc) {
		System.out.println("Qual data de contratação deseja pesquisar");
		String data = sc.next();
		LocalDate time = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionarios = repository.findDataContratacaomaior(time);
		
		funcionarios.forEach(System.out::println);
	}
}
