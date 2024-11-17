package Entity;

import java.time.LocalDate;

public class StatisticsView {
    private int id;
    private int view;
    private int addToCart;
    private int checkOut;
    private LocalDate createAtDate;

    public StatisticsView(){;}

    public StatisticsView(int id, int view, int addToCart, int checkOut, LocalDate createAtDate) {
        this.id = id;
        this.view = view;
        this.addToCart = addToCart;
        this.checkOut = checkOut;
        this.createAtDate = createAtDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(int addToCart) {
        this.addToCart = addToCart;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(LocalDate createAtDate) {
        this.createAtDate = createAtDate;
    }

    public int getMonthOfDate(){
        return this.createAtDate.getMonthValue();
    }

    public int getYearOfDate(){
        return this.createAtDate.getYear();
    }

    @Override
    public String toString() {
        return "StatisticsView{" +
                "id=" + id +
                ", view=" + view +
                ", addToCart=" + addToCart +
                ", checkOut=" + checkOut +
                ", createAtDate=" + createAtDate +
                '}';
    }
}
