package br.com.alura.Spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.Funcionario;
import br.com.alura.Spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	private Boolean system = true;
	
	private final FuncionarioRepository repository;
	
	public RelatorioService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			
			int action = sc.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(sc);
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
}
