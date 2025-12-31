package headfirst.designpatterns.strategy.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
    private static final int SCALE = 2;
    private final BigDecimal value;

    private Money(BigDecimal v) {
        this.value = v.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static Money eur(String s) { return new Money(new BigDecimal(s)); }
    public static Money eur(BigDecimal v) { return new Money(v); }
    public static Money zero() { return eur("0"); }

    public Money plus(Money other) { return eur(this.value.add(other.value)); }
    public Money minus(Money other) { return eur(this.value.subtract(other.value)); }
    public Money times(int qty) { return eur(this.value.multiply(BigDecimal.valueOf(qty))); }

    public Money percent(int pct) {
        BigDecimal p = BigDecimal.valueOf(pct).divide(BigDecimal.valueOf(100), SCALE, RoundingMode.HALF_UP);
        return eur(this.value.multiply(p));
    }

    public Money max(Money other) { return this.value.compareTo(other.value) >= 0 ? this : other; }
    public BigDecimal asBigDecimal() { return value; }

    @Override public String toString() { return value.toPlainString(); }
}
