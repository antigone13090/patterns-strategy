package headfirst.designpatterns.strategy.ecommerce;

public final class FixedShippingAfterDiscounts implements ShippingStrategy {
    private final Money fixed;
    private final Money freeThresholdAfterDiscounts;

    public FixedShippingAfterDiscounts(Money fixed, Money freeThresholdAfterDiscounts) {
        this.fixed = fixed;
        this.freeThresholdAfterDiscounts = freeThresholdAfterDiscounts;
    }

    @Override public String name() { return "FIXED_AFTER_DISCOUNTS"; }

    @Override
    public Money shippingCost(Cart cart, Money totalAfterDiscounts, CheckoutContext ctx) {
        // règle e-commerce réaliste : le seuil de gratuité s’évalue après remises
        if (totalAfterDiscounts.asBigDecimal().compareTo(freeThresholdAfterDiscounts.asBigDecimal()) >= 0) {
            return Money.zero();
        }
        return fixed;
    }
}
