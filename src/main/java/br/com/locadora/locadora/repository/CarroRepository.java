package br.com.locadora.locadora.repository;

import br.com.locadora.locadora.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByModelo(String modelo);

    List<Carro> findByDisponivel(Boolean disponivel);
}
