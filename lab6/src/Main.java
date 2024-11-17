import Controller.StatisticsController;

public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.in.txt";
        String fileOutPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/data/statistics.out.txt";

        StatisticsController statisticsController = new StatisticsController();
        statisticsController.processStatistics(fileInPathStatistics, fileOutPathStatistics);
    }
}