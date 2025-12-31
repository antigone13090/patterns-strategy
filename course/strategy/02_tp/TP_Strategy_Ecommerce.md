# TP Strategy — E-commerce (Remises + Livraison)

## Contexte marché (réel)
Dans un e-commerce, les règles de prix changent souvent :
- promos temporaires, codes marketing, fidélité, règles pays/TVA
- seuils de livraison gratuite qui évoluent
- A/B tests, partenariats (marketplace), segmentation client

Objectif : rendre ces règles **remplaçables** sans casser le reste du code.

---
## Règles fonctionnelles (à implémenter)
### 1) Remises (cumul)
- Remises **cumulatives** : `fidélité + code promo`
- Plafond global : **remise totale ≤ 25%** du sous-total (avant remises)

### 2) Livraison
- Mode **STANDARD** : gratuite si **sous-total après remises ≥ 80€**
  - sinon : 4.90€
- Mode **EXPRESS** : 9.90€ (jamais gratuit)

### 3) Taxes (option cours)
- Calculer la TVA sur : `(sous-total après remises + livraison)` selon pays (FR=20% par ex.)

---
## Ce qu’on attend en code (sans tout te donner)
Implémente une architecture Strategy :
- `DiscountStrategy` : calcule la remise (fidélité, code, ou composition)
- `ShippingStrategy` : calcule le coût livraison (standard/express)
- `TaxStrategy` : calcule les taxes selon pays

Le point clé : le moteur de commande ne doit pas contenir de `if/else` métier en cascade.

---
## Contrat de calcul (ordre)
1) `subtotal = sum(items)`
2) `discount = min(subtotal * 0.25, loyaltyDiscount + promoDiscount)`
3) `subtotalAfterDiscount = subtotal - discount`
4) `shipping = ShippingStrategy(subtotalAfterDiscount, mode)`
5) `tax = TaxStrategy(subtotalAfterDiscount + shipping, country)`
6) `total = subtotalAfterDiscount + shipping + tax`

---
## Livrables
- Diagramme simple (classes/interfaces)
- Code Java + tests unitaires
- 1 scénario “bug” expliqué (ex: remise qui dépasse 25% ou livraison mal calculée)

---
## Extension (exercice)
Ajouter un nouveau code promo “WELCOME10” (10%) mais non cumulable avec un code déjà présent.
(La fidélité reste cumulable.)
