package Service;

import Entity.CRStatistics;
import Entity.StatisticsOutput;
import Entity.StatisticsView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsService {
    public List<StatisticsOutput> calculateStatistics(List<StatisticsView> statisticsViews) {
        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatistics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listCR -> {
                                    CRStatistics crStatistics = new CRStatistics();
                                    StatisticsView firstStatistics = listCR.getFirst();

                                    int totalView = listCR.stream().mapToInt(StatisticsView::getView).sum();

                                    crStatistics.setId(firstStatistics.getId());
                                    crStatistics.setMonth(firstStatistics.getMonthOfDate());
                                    crStatistics.setYear(firstStatistics.getYearOfDate());

                                    crStatistics.setTotalView(totalView);
                                    crStatistics.setTotalAddToCart(listCR.stream().mapToInt(StatisticsView::getAddToCart).sum());
                                    crStatistics.setTotalCheckOut(listCR.stream().mapToInt(StatisticsView::getCheckOut).sum());

                                    return crStatistics;
                                }
                        )
                ));

        return statisticsViews.stream()
                .map(statisticsView -> {
                    CRStatistics key = new CRStatistics(statisticsView.getId(), statisticsView.getMonthOfDate(), statisticsView.getYearOfDate());
                    CRStatistics crStatistics = dataCRS.get(key);

                    double viewPercentage = 0.0;
                    double addToCartPercentage = 0.0;
                    double checkOutPercentage = 0.0;

                    if (crStatistics != null) {
                        viewPercentage = (double) statisticsView.getView() / crStatistics.getTotalView() * 100;
                        addToCartPercentage = (double) statisticsView.getAddToCart() / crStatistics.getTotalView() * 100;
                        checkOutPercentage = (double) statisticsView.getCheckOut() / crStatistics.getTotalView() * 100;
                    }

                    return new StatisticsOutput(statisticsView.getId(), viewPercentage, addToCartPercentage, checkOutPercentage, statisticsView.getMonthOfDate(), statisticsView.getYearOfDate());
                })
                .collect(Collectors.toList());
    }
}
