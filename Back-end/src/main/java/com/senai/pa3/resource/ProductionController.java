package com.senai.pa3.resource;

import com.senai.pa3.dto.ProductionDTO;
import com.senai.pa3.services.ProductionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/production")
public class ProductionController {

    // DESENVOLVIDO PARA O APONTAMENTO DE PRODUÇÃO

    private static final Logger LOGGER = Logger.getLogger(ProductionController.class.getName());
    private ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductionDTO>> findAll() {
        LOGGER.info("Iniciado a busca de todos os produtos");
        var response = productionService.findAll(); // TODO: Implementar o método findAll na classe ProductionService @Gilson
        LOGGER.info("Finalizado a busca de todos os produtos");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductionDTO> findById(@PathVariable Long id) {
        LOGGER.info("Iniciado a busca do produto por ID");
        var response = productionService.findById(id); // TODO: Implementar o método findById na classe ProductionService @Gilson
        LOGGER.info("Finalizado a busca do produto por ID");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductionDTO> insert(@Valid @RequestBody ProductionDTO productionDTO) {
        LOGGER.info("Iniciado a inserção de um novo produto");
        var response = productionService.insert(productionDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(response.getIdProduction()).toUri();
        LOGGER.info("Finalizado a inserção de um novo produto");
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductionDTO> update(@PathVariable Long id, @Valid @RequestBody ProductionDTO productionDTO) {
        LOGGER.info("Iniciado a atualização de um produto");
        var response = productionService.update(id, productionDTO);
        LOGGER.info("Finalizado a atualização de um produto");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Iniciado a exclusão de produto");
        productionService.delete(id); // TODO: Implementar o método delete na classe ProductionService @Gilson
        LOGGER.info("Finalizado a exclusão de um produto");
        return ResponseEntity.noContent().build();
    }
}
