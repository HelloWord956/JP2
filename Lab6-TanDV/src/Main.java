import Entity.CRStatistics;
import Entity.StatisticsOutput;
import Entity.StatisticsView;
import Service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.in.txt";
        String fileOutPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.out.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(fileInPathStatistics);
        List<StatisticsOutput> statisticsOutputs = new ArrayList<>();

        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatistics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listCR -> {
                                    CRStatistics crStatistics = new CRStatistics();
                                    StatisticsView firsStatistics = listCR.getFirst();

                                    int totalView = listCR.stream().mapToInt(StatisticsView::getView).sum();

                                    crStatistics.setId(firsStatistics.getId());
                                    crStatistics.setMonth(firsStatistics.getMonthOfDate());
                                    crStatistics.setYear(firsStatistics.getYearOfDate());

                                    crStatistics.setTotalView(totalView);
                                    crStatistics.setTotalAddToCart(listCR.stream().mapToInt(StatisticsView::getAddToCart).sum());
                                    crStatistics.setTotalCheckOut(listCR.stream().mapToInt(StatisticsView::getCheckOut).sum());

                                    return crStatistics;
                                }
                        )
                ));

        statisticsViews.forEach(statisticsView -> {
            int id = statisticsView.getId();
            int month = statisticsView.getMonthOfDate();
            int year = statisticsView.getYearOfDate();
            double viewPercentage = 0.0;
            double addToCartPercentage = 0.0;
            double checkOutPercentage = 0.0;

            for (Map.Entry<CRStatistics, CRStatistics> entry : dataCRS.entrySet()) {
                CRStatistics crStatistics = entry.getValue();

                if (crStatistics.getMonth() == month && crStatistics.getYear() == year) {
                    viewPercentage = (double) statisticsView.getView() / crStatistics.getTotalView() * 100;
                    addToCartPercentage = (double) statisticsView.getAddToCart() / crStatistics.getTotalView() * 100;
                    checkOutPercentage = (double) statisticsView.getCheckOut() / crStatistics.getTotalView() * 100;
                    break;
                }
            }

            statisticsOutputs.add(new StatisticsOutput(id, viewPercentage, addToCartPercentage, checkOutPercentage, month, year));
        });

        fileService.writeFileStatistics(fileOutPathStatistics, statisticsOutputs);
    }
}