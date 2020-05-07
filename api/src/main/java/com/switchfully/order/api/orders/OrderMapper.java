package com.switchfully.order.api.orders;

import com.switchfully.order.api.customers.addresses.AddressMapper;
import com.switchfully.order.api.orders.dtos.ItemGroupDto;
import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import com.switchfully.order.api.orders.dtos.reports.OrdersReportDto;
import com.switchfully.order.api.orders.dtos.reports.SingleOrderReportDto;
import com.switchfully.order.domain.customers.addresses.Address;
import com.switchfully.order.domain.orders.Order;
import com.switchfully.order.service.customers.CustomerService;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.switchfully.order.domain.orders.Order.OrderBuilder.order;

@Named
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;

    public OrderMapper(OrderItemMapper orderItemMapper, AddressMapper addressMapper, CustomerService customerService) {
        this.orderItemMapper = orderItemMapper;
        this.addressMapper = addressMapper;
        this.customerService = customerService;
    }

    public OrderDto toDto(Order order) {
        return new OrderDto()
                .withOrderId(order.getId().toString())
                .withItemGroups(order.getOrderItems().stream()
                        .map(orderItem -> orderItemMapper.toDto(orderItem))
                        .toArray(ItemGroupDto[]::new))
                .withAddress(addressMapper.toDto(getAddressForCustomer(order.getCustomerId())));
    }

    private Address getAddressForCustomer(UUID customerId) {
        return customerService.getCustomer(customerId).getAddress();
    }

    public Order toDomain(OrderCreationDto orderCreationDto) {
        return order()
                .withCustomerId(UUID.fromString(orderCreationDto.getCustomerId()))
                .withOrderItems(orderCreationDto.getItemGroups().stream()
                        .map(itemGroup -> orderItemMapper.toDomain(itemGroup))
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderAfterCreationDto toOrderAfterCreationDto(Order order) {
        return new OrderAfterCreationDto()
                .withOrderId(order.getId().toString())
                .withTotalPrice(order.getTotalPrice().getAmountAsFloat());
    }

    public OrdersReportDto toOrdersReportDto(List<Order> orders) {
        return new OrdersReportDto()
                .withOrders(orders.stream()
                        .map(this::toSingleOrderReportDto)
                        .collect(Collectors.toList()))
                .withTotalPriceOfAllOrders(orders.stream()
                        .map(order -> order.getTotalPrice().getAmountAsFloat())
                        .reduce(0f, (totalPrice1, totalPrice2) -> totalPrice1 + totalPrice2));
    }

    private SingleOrderReportDto toSingleOrderReportDto(Order order) {
        return new SingleOrderReportDto()
                .withOrderId(order.getId().toString())
                .withItemGroups(order.getOrderItems().stream()
                        .map(orderItem -> orderItemMapper.toItemGroupReportDto(orderItem))
                        .collect(Collectors.toList()));
    }
}
