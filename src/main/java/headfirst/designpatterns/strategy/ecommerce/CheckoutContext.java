package headfirst.designpatterns.strategy.ecommerce;

public record CheckoutContext(boolean loyaltyMember, String promoCode) {
    public String promoCodeSafe() { return promoCode == null ? "" : promoCode.trim(); }
}
