package Entity;

import java.util.Objects;

public class CRStatistics {
    private int id;
    private int month;
    private int year;
    private int totalView;
    private int totalAddToCart;
    private int totalCheckOut;

    public CRStatistics(){;}

    public CRStatistics(int id, int month, int year) {
        this.id = id;
        this.month = month;
        this.year = year;
    }

    public CRStatistics(int id, int month, int year, int totalView, int totalAddToCart, int totalCheckOut) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.totalView = totalView;
        this.totalAddToCart = totalAddToCart;
        this.totalCheckOut = totalCheckOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public int getTotalAddToCart() {
        return totalAddToCart;
    }

    public void setTotalAddToCart(int totalAddToCart) {
        this.totalAddToCart = totalAddToCart;
    }

    public int getTotalCheckOut() {
        return totalCheckOut;
    }

    public void setTotalCheckOut(int totalCheckOut) {
        this.totalCheckOut = totalCheckOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CRStatistics that = (CRStatistics) o;
        return id == that.id && month == that.month && year == that.year && totalView == that.totalView && totalAddToCart == that.totalAddToCart && totalCheckOut == that.totalCheckOut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, year, totalView, totalAddToCart, totalCheckOut);
    }

    @Override
    public String toString() {
        return "CRStatistics{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                ", totalView=" + totalView +
                ", totalAddToCart=" + totalAddToCart +
                ", totalCheckOut=" + totalCheckOut +
                '}';
    }
}
