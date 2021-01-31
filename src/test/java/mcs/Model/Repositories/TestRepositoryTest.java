package mcs.Model.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TestRepositoryTest {

    @Autowired
    TestRepository testRepository;

    @Test void save() {
        testRepository.save(new mcs.Model.Test());
        testRepository.flush();
    }
}