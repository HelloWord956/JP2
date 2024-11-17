import Entity.OrderDetail;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "../data/orderDetail.in.txt";
        String outputFile = "../data/orderDetail.out.txt";
        try {
            List<OrderDetail> orderDetails = readFile(inputFile);

            Map<Integer, Double> customerBill = calculateBilling(orderDetails);

            writeBillingToFile(customerBill, outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<OrderDetail> readFile(String fileName) throws IOException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            int id = Integer.parseInt(data[0]);
            int orderId = Integer.parseInt(data[1]);
            int productId = Integer.parseInt(data[2]);
            int quantity = Integer.parseInt(data[3]);
            double price = Double.parseDouble(data[4]);

            OrderDetail orderDetail = new OrderDetail(id, orderId, productId, quantity, price);
            orderDetails.add(orderDetail);
        }
        br.close();
        return orderDetails;
    }

    private static Map<Integer, Double> calculateBilling(List<OrderDetail> orderDetails) {
        Map<Integer, Double> customerBill = new HashMap<>();

        orderDetails.forEach(orderDetail -> {
            double totalPrice = orderDetail.getTotalPrice();
            int customerId = orderDetail.getOrderId();

            customerBill.put(customerId, customerBill.getOrDefault(customerId, 0.0) + totalPrice);
        });

        return customerBill;
    }

    private static void writeBillingToFile(Map<Integer, Double> customerBill, String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for (Map.Entry<Integer, Double> entry : customerBill.entrySet()) {
            int customerId = entry.getKey();
            double totalAmount = entry.getValue();
            bw.write(customerId + ';' + String.format("%.2f", totalAmount) + "\n");
        }

        bw.close();
    }
}

