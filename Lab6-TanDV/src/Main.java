import Controller.ProductStatisticController;
import Service.FileService;
import Service.ProductStatisticService;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\admin\\IdeaProjects2\\Lab6-TanDV\\data\\data.in.txt";
        String outputFilePath = "C:\\Users\\admin\\IdeaProjects2\\Lab6-TanDV\\data\\data.out.txt";

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        ProductStatisticService pS = new ProductStatisticService();
        FileService fS = new FileService();

        ProductStatisticController pc = new ProductStatisticController(pS, fS);

        pc.processData(inputFilePath, outputFilePath);
    }
}