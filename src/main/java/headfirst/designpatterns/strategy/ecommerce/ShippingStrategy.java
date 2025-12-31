package headfirst.designpatterns.strategy.ecommerce;

public interface ShippingStrategy {
    String name();
    Money shippingCost(Cart cart, Money totalAfterDiscounts, CheckoutContext ctx);
}
