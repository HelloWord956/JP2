package Entity;

import java.util.Objects;

public class StatisticsOutput {
    private int id;
    private double viewPercentage;
    private double addToCartPercentage;
    private double checkOutPercentage;
    private int month;
    private int year;

    public StatisticsOutput() {;}

    public StatisticsOutput(int id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    public StatisticsOutput(int id, double viewPercentage, double addToCartPercentage, double checkOutPercentage, int month, int year) {
        this.id = id;
        this.viewPercentage = viewPercentage;
        this.addToCartPercentage = addToCartPercentage;
        this.checkOutPercentage = checkOutPercentage;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getViewPercentage() {
        return viewPercentage;
    }

    public void setViewPercentage(double viewPercentage) {
        this.viewPercentage = viewPercentage;
    }

    public double getAddToCartPercentage() {
        return addToCartPercentage;
    }

    public void setAddToCartPercentage(double addToCartPercentage) {
        this.addToCartPercentage = addToCartPercentage;
    }

    public double getCheckOutPercentage() {
        return checkOutPercentage;
    }

    public void setCheckOutPercentage(double checkOutPercentage) {
        this.checkOutPercentage = checkOutPercentage;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "StatisticsOutPut{" +
                "id=" + id +
                ", ViewPercentage=" + viewPercentage +
                ", AddToCartPercentage=" + addToCartPercentage +
                ", CheckOutPercentage=" + checkOutPercentage +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
