package com.senai.pa3.services;

import com.senai.pa3.dto.ProductionDTO;
import com.senai.pa3.entities.Production;
import com.senai.pa3.exceptions.ResourceNotFoundException;
import com.senai.pa3.repository.ProductionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionService {

    // DESENVOLVIDO PARA O APONTAMENTO DE PRODUÇÃO

    private final ProductionRepository productionRepository;

    public ProductionService(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductionDTO> findAll() {
        List<Production> productions = productionRepository.findAll();
        return productions.stream().map(ProductionDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductionDTO findById(Long id) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado"));
        return new ProductionDTO(production);
    }

    @Transactional
    public ProductionDTO insert(ProductionDTO productionDTO) {
        Production production = new Production();
        copyDtoToEntity(productionDTO, production);
        production = productionRepository.save(production);
        return new ProductionDTO(production);
    }

    @Transactional
    public ProductionDTO update(Long id, ProductionDTO productionDTO) {
        try {
            var entity = productionRepository.getOne(id);
            copyDtoToEntity(productionDTO, entity);
            entity = productionRepository.save(entity);
            return new ProductionDTO(entity);
        } catch (EntityNotFoundException e ) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!productionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
        productionRepository.deleteById(id);
    }


    private void copyDtoToEntity(ProductionDTO productionDTO, Production production) {
        production.setPlanQuantity(productionDTO.getPlanQuantity());
        production.setRealQuantity(productionDTO.getRealQuantity());
        production.setUnit(productionDTO.getUnit());
        production.setStartTime(productionDTO.getStartTime());
        production.setFinishTime(productionDTO.getFinishTime());
        production.setDowntime(productionDTO.getDowntime());
        production.setPackageType(productionDTO.getPackageType());
        production.setLabelType(productionDTO.getLabelType());
        production.setEquipment(productionDTO.getEquipment());
        production.setWorkShift(productionDTO.getWorkShift());
        production.setProductionBatch(productionDTO.getProductionBatch());
        production.setBestBefore(productionDTO.getBestBefore());
        production.setNotes(productionDTO.getNotes());

    }
}