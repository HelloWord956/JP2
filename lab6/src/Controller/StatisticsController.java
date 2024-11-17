package Controller;

import Entity.StatisticsOutput;
import Entity.StatisticsView;
import Service.StatisticsService;
import Service.FileService;

import java.util.List;

public class StatisticsController {
    private final StatisticsService statisticsService;
    private final FileService fileService;

    public StatisticsController() {
        this.statisticsService = new StatisticsService();
        this.fileService = new FileService();
    }

    public void processStatistics(String inputFilePath, String outputFilePath) {
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(inputFilePath);

        List<StatisticsOutput> statisticsOutputs = statisticsService.calculateStatistics(statisticsViews);

        fileService.writeFileStatistics(outputFilePath, statisticsOutputs);
    }
}