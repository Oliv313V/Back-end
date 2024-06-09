package com.senai.pa3.dto;

import com.senai.pa3.entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long idProduct;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String description;

    public ProductDTO(Product entity) {
        idProduct = entity.getIdProduct();
        name = entity.getName();
        description = entity.getDescription();
    }
}
