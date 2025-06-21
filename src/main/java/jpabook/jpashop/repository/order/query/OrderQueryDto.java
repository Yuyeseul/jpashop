package jpabook.jpashop.repository.order.query;

import jpabook.jpashop.api.OrderApiController;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {

    private Long orderId;                       // 주문 id
    private String name;                        // 주문자명
    private LocalDateTime orderDate;            // 주문시간
    private OrderStatus orderStatus;            // 주문상태
    private Address address;                    // 배송지 정보
    private List<OrderItemQueryDto> orderItems;  // 주문상품정보

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
