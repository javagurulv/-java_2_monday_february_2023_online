package shop.core.services.fake.fake_item_generator;

import shop.core.domain.Item;

import java.util.List;

public interface ItemGenerator {

    List<Item> createItems();

}
