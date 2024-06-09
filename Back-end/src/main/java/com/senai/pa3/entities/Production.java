package com.senai.pa3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_production")
public class Production implements Serializable {

    // DESENVOLVIDO PARA O APONTAMENTO DE PRODUÇÃO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduction;

//    @ManyToOne
//    @JoinColumn(name = "productId")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;

    private Long productId;     //relacionar com tabela tb_product
    private Long userId;    // relacionar com tabela tb_user
    private Float planQuantity;
    private Float realQuantity;
    private String unit;
    private LocalDateTime startTime;    // retornar para LocalDateTime
    private LocalDateTime finishTime;   // retornar para LocalDateTime
    private String downtime; // tempo de parada  // retornar para LocalDateTime
    private String packageType;
    private String labelType;
    private String equipment;
    private String workShift;   // turno de produção
    private String productionBatch; // lote de produção
    private String bestBefore;  // validade do produto
    private String notes;   // observações
}
