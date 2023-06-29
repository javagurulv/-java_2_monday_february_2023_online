package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.ItemConverter;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.support.ItemListRuleConverter;

import java.util.List;

@Component
@Transactional
public class ListShopItemsService {

    @Autowired
    private ItemListRuleConverter itemListRuleConverter;
    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private ItemConverter itemConverter;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        List<Item> shopItems;
        if (request.getPagingRule() != null) {
            Pageable pageRequest = itemListRuleConverter.getPageRequest(request.getPagingRule());
            shopItems = itemRepository.findAll(pageRequest).toList();
        } else {
            shopItems = itemRepository.findAll();
        }
        List<ItemDto> shopItemDtos = itemConverter.toItemDto(shopItems);
        return new ListShopItemsResponse(shopItemDtos);
    }

}
