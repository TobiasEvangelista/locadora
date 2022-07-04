package br.com.locadora.locadora.resource;

import br.com.locadora.locadora.model.Carro;
import br.com.locadora.locadora.service.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CarroResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private CarroService carroService;

    private Carro createCarros(){
        Carro carro = new Carro();

        carro.setId(1L);
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setDisponivel(Boolean.TRUE);

        return carro;
    }

    @Test
    void findAll() throws Exception {
        Page<Carro> carroPage = new PageImpl<>(List.of(createCarros()));

        when(carroService.listAll(ArgumentMatchers.any())).thenReturn(carroPage);

        mockMvc.perform(get("/api/carro")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].marca", is("Fiat")))
                .andExpect(jsonPath("$.content[0].modelo", is("Uno")))
                .andExpect(jsonPath("$.content[0].disponivel", is(Boolean.TRUE)));

    }

    @Test
    void findByModelo() throws Exception {
        List<Carro> carroList = new ArrayList<>(List.of(createCarros()));
        String modelo = "Uno";
        when(carroService.findByModelo(ArgumentMatchers.any())).thenReturn(carroList);

        mockMvc.perform(get(String.format("/api/carro/modelo?modelo=%s", modelo))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].marca", is("Fiat")))
                .andExpect(jsonPath("$.[0].modelo", is("Uno")))
                .andExpect(jsonPath("$.[0].disponivel", is(Boolean.TRUE)));

    }

    @Test
    void findByDisponibilidade() throws Exception {
        List<Carro> carroList = new ArrayList<>(List.of(createCarros()));
        String modelo = "true";
        when(carroService.findByModelo(ArgumentMatchers.any())).thenReturn(carroList);

        mockMvc.perform(get(String.format("/api/carro/disponivel?disponivel=%s", modelo))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].marca", is("Fiat")))
                .andExpect(jsonPath("$.[0].modelo", is("Uno")))
                .andExpect(jsonPath("$.[0].disponivel", is(Boolean.TRUE)));
    }

    @Test
    void createCarro() throws Exception {
        when(carroService.createCarro(ArgumentMatchers.any())).thenReturn(createCarros());

        mockMvc.perform(post("/api/carro")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCarros())))
                .andExpect(status().isCreated());

    }

}
