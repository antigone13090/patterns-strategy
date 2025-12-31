package headfirst.designpatterns.strategy.ecommerce;

import java.util.ArrayList;
import java.util.List;

public final class PricingService {
    private final List<DiscountStrategy> discounts;
    private final ShippingStrategy shipping;

    public PricingService(List<DiscountStrategy> discounts, ShippingStrategy shipping) {
        this.discounts = discounts;
        this.shipping = shipping;
    }

    public Invoice price(Cart cart, CheckoutContext ctx) {
        Money subtotal = cart.subtotal();
        Money total = subtotal;

        List<String> applied = new ArrayList<>();
        for (DiscountStrategy d : discounts) {
            Money amount = d.discount(total, cart, ctx);
            if (amount.asBigDecimal().signum() > 0) {
                applied.add(d.name() + "=-" + amount);
                total = total.minus(amount);
                // sécurité : pas de total négatif
                if (total.asBigDecimal().signum() < 0) total = Money.zero();
            }
        }

        Money ship = shipping.shippingCost(cart, total, ctx);
        Money finalTotal = total.plus(ship);

        return new Invoice(subtotal, applied, total, shipping.name(), ship, finalTotal);
    }
}
