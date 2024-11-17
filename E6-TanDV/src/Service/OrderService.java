package Service;

import Entity.OrderDetail;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OrderService {

    private final FileService fileService;

    public OrderService(FileService fileService) {
        this.fileService = fileService;
    }

    public Map<Integer, List<OrderDetail>> groupOrdersByCustomer(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .collect(Collectors.groupingBy(OrderDetail::getOrderId));
    }

    public Map<Integer, Double> calculateTotalByCustomer(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .collect(Collectors.groupingBy(
                        OrderDetail::getOrderId,
                        Collectors.summingDouble(detail -> detail.getQuantity() * detail.getPrice())
                ));
    }

    public void processOrderCustomerBill(String inputFilename, String outputFilename) {
        List<OrderDetail> orderDetails = fileService.readFile(inputFilename);

        Map<Integer, List<OrderDetail>> customerOrders = groupOrdersByCustomer(orderDetails);

        Map<Integer, Double> totalByCustomer = calculateTotalByCustomer(orderDetails);

        fileService.writeFile(customerOrders, totalByCustomer, outputFilename);
    }
}