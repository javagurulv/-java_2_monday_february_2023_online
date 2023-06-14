package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.ItemConverter;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;

import java.util.List;

@Component
@Transactional
public class ListShopItemsService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private ItemConverter itemConverter;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        List<Item> shopItems = itemRepository.findAll();
        List<ItemDto> shopItemDtos = itemConverter.toItemDto(shopItems);
        return new ListShopItemsResponse(shopItemDtos);
    }

}
