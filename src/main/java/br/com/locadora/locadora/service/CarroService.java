package br.com.locadora.locadora.service;

import br.com.locadora.locadora.exception.CarroNotFoundException;
import br.com.locadora.locadora.model.Carro;
import br.com.locadora.locadora.repository.CarroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CarroService {

    private final CarroRepository carroRepository;

    public Page<Carro> listAll(Pageable page) {
        return carroRepository.findAll(page);

    }

    public Carro findById(Long id) throws CarroNotFoundException {
        return verifyIfExists(id);
    }

    public List<Carro> findByModelo(String  modelo) throws CarroNotFoundException {
        return carroRepository.findByModelo(modelo);
    }

    public List<Carro> findByDisponivel(Boolean  disponivel) {
        return carroRepository.findByDisponivel(disponivel);
    }

    public synchronized Carro updateById(Long id, Carro carro) throws CarroNotFoundException {
        verifyIfExists(id);

        carro.setId(id);

        Carro updateCarro = carroRepository.save(carro);

        log.info("Atualizado carro com id: [{}] ", updateCarro.getId());

        return updateCarro;
    }

    public synchronized Carro createCarro(Carro carro) {
        Carro savedCarro = carroRepository.save(carro);

        log.info("Criado carro {}", savedCarro.toString());

        return savedCarro;
    }

    public synchronized void delete(Long id) throws CarroNotFoundException {
        verifyIfExists(id);

        carroRepository.deleteById(id);

        log.info("Excluído carro com id: [{}]", id);
    }

    private Carro verifyIfExists(Long id) throws CarroNotFoundException {
        return carroRepository.findById(id)
                .orElseThrow(() -> new CarroNotFoundException(String.format("Carro não encontrado com id %d ", id)));
    }
}
