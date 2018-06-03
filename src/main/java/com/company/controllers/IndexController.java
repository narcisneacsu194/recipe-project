package com.company.controllers;

import com.company.domain.Category;
import com.company.domain.UnitOfMeasure;
import com.company.repositories.CategoryRepository;
import com.company.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository){
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> category = categoryRepository.getCategoryByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.getUnitOfMeasureByDescription("teaspoon");

        System.out.println("Category ID: " + category.get().getId());
        System.out.println("UOM ID: " + unitOfMeasure.get().getId());

        return "index";
    }
}
