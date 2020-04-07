package es.devfromt.wildplants.dao;

import es.devfromt.wildplants.entities.PlantEntity;

import java.util.List;

public interface PlantDao {
    List<PlantEntity> findAll();
    List<PlantEntity> findAllWithUse();
    PlantEntity findById(Long id);
    PlantEntity findByName(String name);
    PlantEntity save(PlantEntity plant);
    void delete(PlantEntity plant);
}
