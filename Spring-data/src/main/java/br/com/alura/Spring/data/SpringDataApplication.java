package br.com.alura.Spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.alura.Spring.data.service.CargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CargoService service;
	
	private Boolean system = true;
	
	public SpringDataApplication(CargoService service) {
		this.service = service;
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
			
			int action = sc.nextInt();
			
			if(action == 1) {
				service.inicial(sc);
			}else {
				system = false;
			}
		}
		
	}

}
