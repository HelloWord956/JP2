package Controller;

import Service.OrderService;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void processOrderCustomerBill(String inputFilename, String outputFilename) {
        orderService.processOrderCustomerBill(inputFilename, outputFilename);
    }
}