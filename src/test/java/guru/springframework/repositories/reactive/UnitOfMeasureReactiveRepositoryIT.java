package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception{
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void insertDocument(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("description");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        Long count = unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findDescription(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("description");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetch = unitOfMeasureReactiveRepository.findByDescription("description").block();

        assertNotNull(fetch.getId());
    }
}
