package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    // 주문 조회 v1 : 엔티티를 직접 노출
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getId(); // Lazy 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName()); // Lazy 강제 초기화
        }
        return all;
    }

    // 주문 조회 V2 : 엔티티를 DTO로 변환
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    // 주문 조회 V3 : 엔티티를 DTO로 변환 - 페치 조인 최적화 (컬렉션 1개만 가능, 페이징 불가능!)
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    @Data
    static class OrderDto {

        private Long orderId;                   // 주문 id
        private String name;                    // 주문자명
        private LocalDateTime orderDate;        // 주문시간
        private OrderStatus orderStatus;        // 주문상태
        private Address address;                // 배송지 정보
        private List<OrderItemDTo> orderItems;  // 주문상품정보

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDTo(orderItem))
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDTo {

        private String itemName;        // 상품명
        private int orderPrice;         // 주문가격
        private int count;              // 주문수량

        public OrderItemDTo(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
