package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearRepository();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> allItems = itemRepository.findAllItems();

        // then
        assertThat(allItems.size()).isEqualTo(2);
        assertThat(allItems).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long savedItemId = savedItem.getId();

        // when
        Item updateParam = new Item("itemB", 20000, 20);
        itemRepository.updateItem(savedItemId, updateParam);

        Item findItem = itemRepository.findById(savedItemId);
        assertThat(findItem.getName()).isEqualTo(updateParam.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}