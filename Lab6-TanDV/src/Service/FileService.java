package Service;

import Entity.ProductStatistics;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public List<ProductStatistics> readFile(String path) {
        List<ProductStatistics> productStatisticsList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int productId = Integer.parseInt(data[0]);
                int click = Integer.parseInt(data[1]);
                int addToCart = Integer.parseInt(data[2]);
                int checkOut = Integer.parseInt(data[3]);
                LocalDate date = LocalDate.parse(data[4], formatter);

                ProductStatistics product = new ProductStatistics(productId, click, addToCart, checkOut, date);
                productStatisticsList.add(product);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productStatisticsList;
    }

    public void writeFile(String path, List<String> outputLines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (String line : outputLines) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
