package Controller;

import Entity.ProductStatistics;
import Service.FileService;
import Service.ProductStatisticService;

import java.util.List;
import java.util.Map;

public class ProductStatisticController {
    private ProductStatisticService productStatisticService;
    private FileService fileService;
    public ProductStatisticController(ProductStatisticService productStatisticService , FileService fileService) {
        this.productStatisticService = productStatisticService;
        this.fileService = fileService;
    }

    public void processData(String inputFilePath, String outputFilePath) {
        // Read data from input file
        List<ProductStatistics> productStatisticsList = fileService.readFile(inputFilePath);

        // Aggregate data for each product
        Map<String, Map<Integer, ProductStatistics>> aggregetedData = productStatisticService.aggregateData(productStatisticsList);

        // Calculate percentage for each product
        List<String> outputLines = productStatisticService.calculatePercentages(aggregetedData);

        // Write the results to the output file
        fileService.writeFile(outputFilePath, outputLines);
    }
}
