package mcs.Model.Repositories;

import mcs.Model.ItemGroup;
import mcs.Model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ItemGroupRepositoryTest {

    private final ItemGroupRepository itemGroupRepository;

    @Autowired
    public ItemGroupRepositoryTest(ItemGroupRepository itemGroupRepository) {
        this.itemGroupRepository = itemGroupRepository;
    }
    @Test
    void saveItemGroup() {
        var itemGrpTest = new ItemGroup();
        itemGrpTest.addItem(new Product("Some Product", "Some desc", 23d), 2);
        itemGroupRepository.save(itemGrpTest);
        itemGroupRepository.flush();
        assertNotNull(itemGroupRepository.findById(itemGrpTest.getId()));
    }
}