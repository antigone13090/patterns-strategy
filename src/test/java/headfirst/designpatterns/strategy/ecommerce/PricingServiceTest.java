package headfirst.designpatterns.strategy.ecommerce;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    void discounts_are_cumulative_loyalty_then_promo() {
        PricingService service = new PricingService(
                List.of(new LoyaltyDiscount(5), new PromoCodeDiscount("NOEL10", 10)),
                new FixedShippingAfterDiscounts(Money.eur("4.90"), Money.eur("50.00"))
        );

        Cart cart = new Cart();
        cart.add(new CartItem("SKU-A", Money.eur("100.00"), 1));

        Invoice inv = service.price(cart, new CheckoutContext(true, "NOEL10"));

        // 100 -5% => 95.00 ; -10% => 85.50
        assertEquals("100.00", inv.subtotal().toString());
        assertEquals("85.50", inv.totalAfterDiscounts().toString());
    }

    @Test
    void shipping_threshold_is_evaluated_after_discounts() {
        PricingService service = new PricingService(
                List.of(new LoyaltyDiscount(5), new PromoCodeDiscount("NOEL10", 10)),
                new FixedShippingAfterDiscounts(Money.eur("4.90"), Money.eur("50.00"))
        );

        Cart cart = new Cart();
        cart.add(new CartItem("SKU-B", Money.eur("27.50"), 2)); // 55.00

        Invoice inv = service.price(cart, new CheckoutContext(true, "NOEL10"));

        // 55.00 -5% => 52.25 ; -10% => 47.02 ; shipping (after discounts < 50) => 4.90 ; final=51.92
        assertEquals("47.02", inv.totalAfterDiscounts().toString());
        assertEquals("4.90", inv.shippingCost().toString());
        assertEquals("51.92", inv.finalTotal().toString());
    }

    @Test
    void free_shipping_when_total_after_discounts_reaches_threshold() {
        PricingService service = new PricingService(
                List.of(new LoyaltyDiscount(5), new PromoCodeDiscount("NOEL10", 10)),
                new FixedShippingAfterDiscounts(Money.eur("4.90"), Money.eur("50.00"))
        );

        Cart cart = new Cart();
        cart.add(new CartItem("SKU-C", Money.eur("60.00"), 1)); // 60.00

        Invoice inv = service.price(cart, new CheckoutContext(true, "NOEL10"));

        // 60.00 -> after discounts = 51.30 >= 50 => shipping free
        assertEquals("51.30", inv.totalAfterDiscounts().toString());
        assertEquals("0.00", inv.shippingCost().toString());
        assertEquals("51.30", inv.finalTotal().toString());
    }
}
