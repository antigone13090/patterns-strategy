package headfirst.designpatterns.strategy.ecommerce;

public interface DiscountStrategy {
    String name();
    Money discount(Money currentTotal, Cart cart, CheckoutContext ctx);
}
