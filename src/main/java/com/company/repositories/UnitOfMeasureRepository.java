package com.company.repositories;

import com.company.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
    Optional<UnitOfMeasure> getUnitOfMeasureByDescription(String description);
}
