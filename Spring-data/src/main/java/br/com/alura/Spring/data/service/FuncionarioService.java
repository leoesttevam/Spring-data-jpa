package br.com.alura.Spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.Spring.data.orm.Cargo;
import br.com.alura.Spring.data.orm.Funcionario;
import br.com.alura.Spring.data.orm.UnidadeTrabalho;
import br.com.alura.Spring.data.repository.CargoRepository;
import br.com.alura.Spring.data.repository.FuncionarioRepository;
import br.com.alura.Spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class FuncionarioService {

	private final FuncionarioRepository repository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository trabalhoRepository;
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public FuncionarioService(FuncionarioRepository repository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository trabalhoRepository) {
		this.repository = repository;
		this.cargoRepository = cargoRepository;
		this.trabalhoRepository = trabalhoRepository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de Funcionario deseja executar");
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
		System.out.println("Digite o dados do funcionario");
		
		System.out.println("Nome");
		String nome = sc.next();
		
		System.out.println("CPF");
		String cpf = sc.next();
		
		System.out.println("Salario");
		BigDecimal salario = sc.nextBigDecimal();
		
		System.out.println("Data da contratação");
		String data = sc.next();
		
		System.out.println("Id do cargo");
		Integer id = sc.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(sc);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCPF(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(data, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(id);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidade(unidades);
		
		
		repository.save(funcionario);
		
		System.out.println("Salvo");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner sc) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite a unidadeId (para sair digite 0)");
			Integer unidadeId = sc.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = trabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			}else {
				isTrue = false;
			}
		}
		
		return unidades;
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		
		System.out.println("Nome");
		String nome = sc.next();
		
		System.out.println("CPF");
		String cpf = sc.next();
		
		System.out.println("Salario");
		BigDecimal salario = sc.nextBigDecimal();
		
		System.out.println("Data da contratação");
		String data = sc.next();
		
		System.out.println("Id do cargo");
		Integer cargoId = sc.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(sc);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCPF(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(data, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidade(unidades);
		
		repository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionario = repository.findAll();
		
		funcionario.forEach(func -> System.out.println(func));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		repository.deleteById(id);
		System.out.println("Deletado");
	}
}
