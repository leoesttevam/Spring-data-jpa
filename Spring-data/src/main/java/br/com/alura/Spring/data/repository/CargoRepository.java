package br.com.alura.Spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.Spring.data.orm.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
