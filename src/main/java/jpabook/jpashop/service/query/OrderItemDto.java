package jpabook.jpashop.service.query;

import jpabook.jpashop.domain.OrderItem;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class OrderItemDto {

    private String itemName;        // 상품명
    private int orderPrice;         // 주문가격
    private int count;              // 주문수량

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}