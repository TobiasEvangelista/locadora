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

    public List<Carro> listAll(Pageable page) {
        Page<Carro> carros = carroRepository.findAll(page);
        return carros.stream().collect(Collectors.toList());
    }

    public Carro findById(Long id) throws CarroNotFoundException {
        Carro carro = verifyIfExists(id);
        return carro;
    }

    public List<Carro> findByModelo(String  modelo) throws CarroNotFoundException {
        List<Carro> carros = carroRepository.findByModelo(modelo);
        return carros;
    }

    public List<Carro> findByDisponivel(Boolean  disponivel) {
        List<Carro> carros = carroRepository.findByDisponivel(disponivel);
        return carros;
    }

    public synchronized Carro updateById(Long id, Carro carro) throws CarroNotFoundException {
        Carro carroToUpdate = carro;

        verifyIfExists(id);

        carroToUpdate.setId(id);

        Carro updateCarro = carroRepository.save(carroToUpdate);

        log.info("Atualizado carro com id: [{}] ", carro.getId());
        return updateCarro;
    }

    public synchronized Carro createCarro(Carro carro) {
        Carro carroToSave = carro;
        Carro savedCarro = carroRepository.save(carroToSave);

        log.info("Criado carro com id [{}]", carro.getId());
        return savedCarro;
    }

    public synchronized ResponseEntity delete(Long id) throws CarroNotFoundException {
        verifyIfExists(id);
        carroRepository.deleteById(id);
        log.info("Excluído carro com id: [{}]", id);
        return ResponseEntity.ok(String.format("Carro excluído com sucesso"));
    }

    private Carro verifyIfExists(Long id) throws CarroNotFoundException {
        return carroRepository.findById(id)
                .orElseThrow(() -> new CarroNotFoundException(String.format("Carro não encontrado com id %d ", id)));
    }
}
