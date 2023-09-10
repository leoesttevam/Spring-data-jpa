package br.com.alura.Spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.UnidadeTrabalho;
import br.com.alura.Spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class UnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository repository;
	
	private Boolean system = true;
	
	public UnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de Unidade de trabalhoss deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = sc.nextInt();
			
			switch (action) {
			case 1:
				salvar(sc);
				break;
			case 2:
				atualizar(sc);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(sc);
				break;
			default:
				system = false;
				break;
			}
		}
		
		salvar(sc);
	}
	
	public void salvar(Scanner sc) {
		System.out.println("Digite o nome da unidade de trabalho");
		String descricao = sc.next();
		
		System.out.println("Digite o endereco");
		String endereco = sc.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho(descricao, endereco);
		
		repository.save(unidade);
		
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		
		System.out.println("Digite o nome da unidade de trabalho");
		String descricao = sc.next();
		
		System.out.println("Digite o endereco");
		String endereco = sc.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		repository.save(unidade);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidade = repository.findAll();
		
		unidade.forEach(uni -> System.out.println(uni));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		repository.deleteById(id);
		System.out.println("Deletado");
	}
}
