package headfirst.designpatterns.strategy.ecommerce;

import java.util.List;

public record Invoice(
        Money subtotal,
        List<String> appliedDiscounts,
        Money totalAfterDiscounts,
        String shippingRule,
        Money shippingCost,
        Money finalTotal
) {}
