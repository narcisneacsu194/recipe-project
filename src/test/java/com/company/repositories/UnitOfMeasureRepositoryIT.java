package com.company.repositories;

import com.company.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void getUnitOfMeasureByDescription() throws Exception {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.getUnitOfMeasureByDescription("teaspoon");

        assertEquals("teaspoon", uomOptional.get().getDescription());
    }

    @Test
    public void getUnitOfMeasureByDescriptionCup() throws Exception {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.getUnitOfMeasureByDescription("cup");

        assertEquals("cup", uomOptional.get().getDescription());
    }

}