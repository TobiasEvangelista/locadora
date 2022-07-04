package br.com.locadora.locadora.resource;

import br.com.locadora.locadora.exception.CarroNotFoundException;
import br.com.locadora.locadora.model.Carro;
import br.com.locadora.locadora.service.CarroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carro")
public class CarroResource {

    private final CarroService carroService;

    public CarroResource(CarroService carroService) {
        this.carroService = carroService;
    }

    @ApiOperation(value = "Retorna uma lista paginada de carros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista paginada de carros"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<Page<Carro>> findAll(@PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 2) Pageable page) {
        return ResponseEntity.ok(carroService.listAll(page));
    }

    @ApiOperation(value = "Retorna um carro pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um carro pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) throws CarroNotFoundException {
        return ResponseEntity.ok(carroService.findById(id));
    }

    @ApiOperation(value = "Retorna uma lista de carros pelo modelo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de carros pelo modelo"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/modelo")
    public ResponseEntity<List<Carro>> findByModelo(@RequestParam(value="modelo") String modelo) throws CarroNotFoundException {
        return ResponseEntity.ok(carroService.findByModelo(modelo));
    }

    @ApiOperation(value = "Retorna uma lista de carros pela disponibilidade")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de carros pela disponibilidade"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/disponivel")
    public ResponseEntity<List<Carro>> findByDisponibilidade(@RequestParam(value="disponivel") Boolean disponibilidade) {
        return ResponseEntity.ok(carroService.findByDisponivel(disponibilidade));
    }

    @ApiOperation(value = "Retorna o carro que acabou de ser criado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o carro que acabou de ser criado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.createCarro(carro));
    }

    @ApiOperation(value = "Retorna o carro que acabou de ser alterado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o carro que acabou de ser alterado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Carro> UpdateById(@PathVariable Long id, @RequestBody Carro carro) throws CarroNotFoundException {
        return ResponseEntity.ok(carroService.updateById(id,carro));
    }

    @ApiOperation(value = "Endpoint para excluir carro")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteById(@PathVariable Long id) throws CarroNotFoundException {
        carroService.delete(id);
    }
}
