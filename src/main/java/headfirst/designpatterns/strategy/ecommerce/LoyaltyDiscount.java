package headfirst.designpatterns.strategy.ecommerce;

public final class LoyaltyDiscount implements DiscountStrategy {
    private final int percent;

    public LoyaltyDiscount(int percent) { this.percent = percent; }

    @Override public String name() { return "LOYALTY_" + percent + "PCT"; }

    @Override
    public Money discount(Money currentTotal, Cart cart, CheckoutContext ctx) {
        if (!ctx.loyaltyMember()) return Money.zero();
        return currentTotal.percent(percent);
    }
}
