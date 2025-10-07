package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> itemRepository = new HashMap<>();
    private static long sequence = 0L;

    // 상품 저장
    public Item save(Item item) {
        item.setId(++sequence);
        itemRepository.put(item.getId(), item);
        return item;
    }

    // id로 상품 조회
    public Item findById(Long id) {
        return itemRepository.get(id);
    }

    // 모든 상품 조회
    public List<Item> findAllItems() {
        return new ArrayList<>(itemRepository.values());
    }

    // 상품 정보 수정
    public void updateItem(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setName(updateParam.getName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setStatus(updateParam.getStatus());
        findItem.setRegions(updateParam.getRegions());
        findItem.setType(updateParam.getType());
        findItem.setDeliveryCode(updateParam.getDeliveryCode());
    }

    // 저장소 초기화
    public void clearRepository() {
        itemRepository.clear();
    }

}
