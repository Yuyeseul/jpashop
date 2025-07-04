package jpabook.jpashop.repository.order.query;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDto {

    private Long orderId;             // 주문 id
    private String name;              // 주문자명
    private LocalDateTime orderDate;  // 주문시간
    private OrderStatus orderStatus;  // 주문상태
    private Address address;          // 배송지 정보

    private String itemName;          // 상품명
    private int orderPrice;           // 주문가격
    private int count;                // 주문수량

    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
