package Service;

import Entity.ProductStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStatisticService {
    // Tổng hợp dữ liệu của các sản phẩm theo tháng và năm
    public Map<String, Map<Integer, ProductStatistics>> aggregateData(List<ProductStatistics> productStatisticsList) {
        Map<String, Map<Integer, ProductStatistics>> aggregatedData = new HashMap<>();

        for (ProductStatistics product : productStatisticsList) {
            String monthYear = product.getDate().getYear() + "-" + String.format("%02d", product.getDate().getMonthValue());
            aggregatedData.putIfAbsent(monthYear, new HashMap<>());
            Map<Integer, ProductStatistics> monthlyData = aggregatedData.get(monthYear);

            int productId = product.getProductId();
            monthlyData.putIfAbsent(productId, new ProductStatistics(productId, 0, 0, 0, product.getDate()));
            ProductStatistics existingData = monthlyData.get(productId);
            existingData.update(product);
        }
        return aggregatedData;
    }

    // Tính toán tỷ lệ phần trăm cho từng sản phẩm theo tháng/năm
    public List<String> calculatePercentages(Map<String, Map<Integer, ProductStatistics>> aggregatedData) {
        List<String> outputLines = new ArrayList<>();

        for (Map.Entry<String, Map<Integer, ProductStatistics>> monthEntry : aggregatedData.entrySet()) {
            String monthYear = monthEntry.getKey();
            Map<Integer, ProductStatistics> monthlyData = monthEntry.getValue();

            int totalClicks = monthlyData.values().stream().mapToInt(ProductStatistics::getClick).sum();

            for (Map.Entry<Integer, ProductStatistics> productEntry : monthlyData.entrySet()) {
                int productId = productEntry.getKey();
                ProductStatistics data = productEntry.getValue();

                double percentClick = totalClicks == 0 ? 0 : (double) data.getClick() / totalClicks * 100;
                double percentAddToCart = data.getClick() == 0 ? 0 : (double) data.getAddToCart() / data.getClick() * 100;
                double percentCheckOut = data.getClick() == 0 ? 0 : (double) data.getCheckOut() / data.getClick() * 100;

                String outputLine = String.format("%d; %.2f%%; %.2f%%; %.2f%%; %s",
                        productId, percentClick, percentAddToCart, percentCheckOut, monthYear);
                outputLines.add(outputLine);
            }
        }
        return outputLines;
    }
}
