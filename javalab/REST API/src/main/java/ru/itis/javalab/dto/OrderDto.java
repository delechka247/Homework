package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.models.Order;
import ru.itis.javalab.models.User;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private List<DonutDto> donuts;

    public static OrderDto from(Order order) {
        OrderDto result = OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .donuts(DonutDto.from(order.getDonuts()))
                .build();
        return result;
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
