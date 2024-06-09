package com.senai.pa3.repository;

import com.senai.pa3.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// DESENVOLVIDO PARA O APONTAMENTO DE PRODUÇÃO
@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
}

