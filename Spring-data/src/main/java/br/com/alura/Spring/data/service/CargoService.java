package br.com.alura.Spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.Cargo;
import br.com.alura.Spring.data.repository.CargoRepository;

@Service
public class CargoService {

	private final CargoRepository repository;
	
	private Boolean system = true;
	
	public CargoService(CargoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar");
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
		System.out.println("Descrição do cargo");
		String descricao = sc.next();
		Cargo cargo = new Cargo(descricao);
		
		repository.save(cargo);
		
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		
		System.out.println("Descricao do cargo");
		String descricao = sc.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		
		repository.save(cargo);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		List<Cargo> cargos = repository.findAll();
		
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		repository.deleteById(id);
		System.out.println("Deletado");
	}
}
