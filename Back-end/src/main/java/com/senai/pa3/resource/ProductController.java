package com.senai.pa3.resource;

import com.senai.pa3.dto.ProductDTO;
import com.senai.pa3.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        LOGGER.info("Iniciado a busca de todos os produtos");
        var response = productService.findAll(); // TODO: Implementar o método findAll na classe ProductService @Gilson
        LOGGER.info("Finalizado a busca de todos os produtos");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        LOGGER.info("Iniciado a busca do produto por ID");
        var response = productService.findById(id); // TODO: Implementar o método findById na classe ProductService @Gilson
        LOGGER.info("Finalizado a busca do produto por ID");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO productDTO) {
        LOGGER.info("Iniciado a inserção de um novo produto");
        var response = productService.insert(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(response.getIdProduct()).toUri();
        LOGGER.info("Finalizado a inserção de um novo produto");
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        LOGGER.info("Iniciado a atualização de um produto");
        var response = productService.update(id, productDTO);
        LOGGER.info("Finalizado a atualização de um produto");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Iniciado a exclusão de produto");
        productService.delete(id); // TODO: Implementar o método delete na classe ProductService @Gilson
        LOGGER.info("Finalizado a exclusão de um produto");
        return ResponseEntity.noContent().build();
    }
}
