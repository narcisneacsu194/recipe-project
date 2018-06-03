package com.company.repositories;

import com.company.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    Optional<Category> getCategoryByDescription(String description);
}
