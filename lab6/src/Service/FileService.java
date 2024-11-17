package Service;

import Entity.StatisticsOutput;
import Entity.StatisticsView;
import General.IFileService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService implements IFileService<StatisticsView> {
    public FileService() {;}

    public List<StatisticsView> readFileStatistics(String fileInPath){
        List<StatisticsView> statisticsViews = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileInPath));
            String line;
            while((line = br.readLine()) != null) {
                StatisticsView statisticsView = new StatisticsView();
                if(!line.isEmpty()) {
                    String[] data = line.split(";");
                    statisticsView.setId(Integer.parseInt(String.valueOf(data[0])));
                    statisticsView.setView(Integer.parseInt(String.valueOf(data[1])));
                    statisticsView.setAddToCart(Integer.parseInt(String.valueOf(data[2])));
                    statisticsView.setCheckOut(Integer.parseInt(String.valueOf(data[3])));
                    statisticsView.setCreateAtDate(LocalDate.parse(String.valueOf(data[4])));
                    statisticsViews.add(statisticsView);
                }
            }
        } catch(IOException e) {
            e.getCause();
        }
        return statisticsViews;
    }

    @Override
    public void writeFileStatistics(String fileOutPath, List<StatisticsOutput> statisticsOutputs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutPath))) {
            for (StatisticsOutput output : statisticsOutputs) {
                String line = String.format("%d, %.2f%%, %.2f%%, %.2f%%, %d-%d",
                        output.getId(),
                        output.getViewPercentage(),
                        output.getAddToCartPercentage(),
                        output.getCheckOutPercentage(),
                        output.getYear(),
                        output.getMonth());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.getCause();
        }
    }
}
