package br.com.locadora.locadora.service;

import br.com.locadora.locadora.exception.CarroNotFoundException;
import br.com.locadora.locadora.model.Carro;
import br.com.locadora.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    private CarroRepository carroRepository;

    private Carro createCarros(){
        Carro carro = new Carro();

        carro.setId(1L);
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setDisponivel(Boolean.TRUE);

        return carro;
    }

    @Test
    void findByModelo() throws CarroNotFoundException {
        List<Carro> carroList = new ArrayList<>(List.of(createCarros()));

        when(carroRepository.findByModelo(any())).thenReturn(carroList);
        assertEquals(carroList, carroService.findByModelo("Uno"));

        verify(carroRepository, times(1)).findByModelo(any());
    }

    @Test
    void findByDisponivel() throws CarroNotFoundException {
        List<Carro> carroList = new ArrayList<>(List.of(createCarros()));

        when(carroRepository.findByDisponivel(any())).thenReturn(carroList);
        assertEquals(carroList, carroService.findByDisponivel(Boolean.TRUE));

        verify(carroRepository, times(1)).findByDisponivel(any());
    }

    @Test
    void updateById() throws CarroNotFoundException {
        Carro carro = createCarros();

        when(carroRepository.save(any())).thenReturn(carro);
        when(carroRepository.findById(any())).thenReturn(Optional.of(carro));
        assertEquals(carro, carroService.updateById(1L, carro));

        verify(carroRepository, times(1)).save(any());
    }

    @Test
    void createCarro() throws CarroNotFoundException {
        Carro carro = createCarros();

        when(carroRepository.save(any())).thenReturn(carro);
        assertEquals(carro, carroService.createCarro(carro));

        verify(carroRepository, times(1)).save(any());
    }

}
