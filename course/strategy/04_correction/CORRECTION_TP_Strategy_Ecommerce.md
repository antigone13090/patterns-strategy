# Corrigé — TP Strategy (E-commerce) — Cas B

## Règles attendues (Cas B)
1) **Sous-total (subtotal)**  
   Somme des lignes : `prix_unitaire * quantité`  
   Arrondi **à 2 décimales** (BigDecimal, HALF_UP).

2) **Remises cumulatives** (fidélité + code promo)  
   - On applique **plusieurs stratégies de remise**.
   - Les remises **se cumulent** (on applique l’une après l’autre).
   - Le total ne doit **jamais** devenir négatif.

3) **Frais de port calculés après remises**  
   - Le port dépend du **total après remises** (pas du sous-total).
   - Exemple typique de règle :  
     - si `totalAprèsRemises >= 50.00` → port = `0.00`  
     - sinon port = `4.90`

4) **Total final**
   `totalFinal = totalAprèsRemises + port`

## Architecture attendue (pattern Strategy)
### A) DiscountStrategy
- Interface `DiscountStrategy`
- Rôle : calculer une remise (ou un nouveau total) **sans que PricingService connaisse la règle**.

Exemples de stratégies :
- `LoyaltyDiscountStrategy` (fidélité)
- `CouponDiscountStrategy` (code promo)

### B) ShippingStrategy
- Interface `ShippingStrategy`
- Rôle : calculer les frais de port selon une règle remplaçable.

Exemple :
- `ThresholdShippingStrategy(threshold=50.00, fee=4.90)`

### C) PricingService (contexte)
- Calcule `subtotal`
- Applique toutes les `DiscountStrategy` (cumul)
- Calcule le port via `ShippingStrategy` **après remises**
- Retourne un objet `Invoice`

## Checklist “ça passe les tests”
- [ ] `subtotal()` retourne une valeur formatée/arrondie à 2 décimales (ex: "100.00")
- [ ] `totalAfterDiscounts()` reflète l’application cumulée des remises
- [ ] `shippingCost()` dépend du total après remises
- [ ] `finalTotal()` = total après remises + port
- [ ] Aucune dépendance directe à un type de remise/port dans PricingService (stratégies injectées)

## Exemples cohérents avec les assertions observées
- `totalAfterDiscounts = 47.02`, `shipping = 4.90` → `final = 51.92`
- `totalAfterDiscounts = 51.30`, `shipping = 0.00` → `final = 51.30`
