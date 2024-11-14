package Entity;

import java.time.LocalDate;

public class ProductStatistics {
    private int productId;
    private int click;
    private int addToCart;
    private int checkOut;
    private LocalDate date;

    public ProductStatistics(){;}
    public ProductStatistics(int productId, int click, int addToCart, int checkOut, LocalDate date) {
        this.productId = productId;
        this.click = click;
        this.addToCart = addToCart;
        this.checkOut = checkOut;
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void update(ProductStatistics newData) {
        this.click += newData.getClick();
        this.addToCart += newData.getAddToCart();
        this.checkOut += newData.getCheckOut();
    }
}
