package headfirst.designpatterns.strategy.ecommerce;

import java.util.Locale;

public final class PromoCodeDiscount implements DiscountStrategy {
    private final String expectedCode;
    private final int percent;

    public PromoCodeDiscount(String expectedCode, int percent) {
        this.expectedCode = expectedCode == null ? "" : expectedCode.trim().toUpperCase(Locale.ROOT);
        this.percent = percent;
    }

    @Override public String name() { return "PROMO_" + expectedCode + "_" + percent + "PCT"; }

    @Override
    public Money discount(Money currentTotal, Cart cart, CheckoutContext ctx) {
        String code = ctx.promoCodeSafe().toUpperCase(Locale.ROOT);
        if (!code.equals(expectedCode)) return Money.zero();
        return currentTotal.percent(percent);
    }
}
