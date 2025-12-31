package headfirst.designpatterns.strategy.ecommerce;

import java.util.List;

public final class EcommerceDemo {
    public static void main(String[] args) {
        // Cas B : fidélité + code promo (cumul), livraison fixe évaluée après remises
        PricingService service = new PricingService(
                List.of(
                        new LoyaltyDiscount(5),
                        new PromoCodeDiscount("NOEL10", 10)
                ),
                new FixedShippingAfterDiscounts(Money.eur("4.90"), Money.eur("50.00"))
        );

        Cart cart = new Cart();
        cart.add(new CartItem("SKU-CHAUSSETTES", Money.eur("27.50"), 2)); // subtotal = 55.00

        CheckoutContext ctx = new CheckoutContext(true, "NOEL10");

        Invoice invoice = service.price(cart, ctx);

        System.out.println("SUBTOTAL=" + invoice.subtotal());
        System.out.println("DISCOUNTS=" + invoice.appliedDiscounts());
        System.out.println("AFTER_DISCOUNTS=" + invoice.totalAfterDiscounts());
        System.out.println("SHIPPING(" + invoice.shippingRule() + ")=" + invoice.shippingCost());
        System.out.println("FINAL=" + invoice.finalTotal());
    }
}
