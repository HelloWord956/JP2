import Controller.OrderController;
import Service.FileService;
import Service.OrderService;


public class Main {
    public static void main(String[] args) {
        String fileInputName = System.getProperty("user.dir").replace("/", "\\") + "/data/orderDetail.in.txt";
        String outputFileName = System.getProperty("user.dir").replace("/", "\\") + "/data/orderDetail.out.txt";

        FileService fileService = new FileService();
        OrderService orderService = new OrderService(fileService);
        OrderController orderController = new OrderController(orderService);

        orderController.processOrderCustomerBill(fileInputName, outputFileName);
    }
}

