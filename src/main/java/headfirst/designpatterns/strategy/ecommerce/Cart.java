package headfirst.designpatterns.strategy.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(CartItem item) { items.add(item); }

    public List<CartItem> items() { return Collections.unmodifiableList(items); }

    public Money subtotal() {
        Money sum = Money.zero();
        for (CartItem it : items) sum = sum.plus(it.lineTotal());
        return sum;
    }
}
