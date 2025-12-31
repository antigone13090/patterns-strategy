package headfirst.designpatterns.strategy.ecommerce;

public record CartItem(String sku, Money unitPrice, int quantity) {
    public Money lineTotal() {
        return unitPrice.times(quantity);
    }
}
