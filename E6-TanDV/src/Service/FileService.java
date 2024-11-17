package Service;

import Entity.OrderDetail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileService {
    public List<OrderDetail> readFile(String inputFileName) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
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
        } catch (IOException e) {
            e.getCause();
        }

        return orderDetails;
    }

    public void writeFile(Map<Integer, List<OrderDetail>> customerOrders, Map<Integer, Double> totalByCustomer, String outputFilename) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));
            for (Map.Entry<Integer, List<OrderDetail>> entry : customerOrders.entrySet()) {
                int customerId = entry.getKey();
                List<OrderDetail> orderDetails = entry.getValue();
                double totalAmount = totalByCustomer.getOrDefault(customerId, 0.0);

                bw.write("Customer ID: " + customerId + "\n");

                orderDetails.forEach(orderDetail -> {
                    double itemTotal = orderDetail.getQuantity() * orderDetail.getPrice();
                    try {
                        bw.write(String.format("Product ID: %d, Quantity: %d, Price: %.2f, Total: %.2f\n",
                                orderDetail.getProductId(), orderDetail.getQuantity(), orderDetail.getPrice(), itemTotal));
                    } catch (IOException e){
                        e.getCause();
                    }
                });
                bw.write(String.format("Total Amount: %.2f\n", totalAmount));
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.getCause();
        }
    }
}
