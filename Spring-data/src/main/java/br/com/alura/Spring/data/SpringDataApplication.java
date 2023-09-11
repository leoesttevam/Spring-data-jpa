package br.com.alura.Spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.alura.Spring.data.service.CargoService;
import br.com.alura.Spring.data.service.FuncionarioService;
import br.com.alura.Spring.data.service.RelatorioService;
import br.com.alura.Spring.data.service.UnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CargoService service;
	private final FuncionarioService funcionarioService;
	private final UnidadeTrabalhoService trabalhoService;
	private final RelatorioService relatorioService;
	
	private Boolean system = true;
	
	public SpringDataApplication(CargoService service, FuncionarioService funcionarioService, 
			UnidadeTrabalhoService trabalhoService, RelatorioService relatorioService) {
		this.service = service;
		this.funcionarioService = funcionarioService;
		this.trabalhoService = trabalhoService;
		this.relatorioService = relatorioService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você deseja executar; ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade Trabalho");
			System.out.println("4 - Relatorios");
			
			int action = sc.nextInt();
			
			switch (action) {
			case 1:
				service.inicial(sc);
				break;
			case 2:
				funcionarioService.inicial(sc);
				break;
			case 3:
				trabalhoService.inicial(sc);
				break;
			case 4:
				relatorioService.inicial(sc);
				break;
			default:
				break;
			}
		}
		
	}

}
