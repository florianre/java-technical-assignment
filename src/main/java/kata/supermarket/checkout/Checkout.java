package kata.supermarket.checkout;

import kata.supermarket.Item;
import kata.supermarket.discount.DiscountManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Checkout {

  private final Basket basket;
  private final DiscountManager discountManager;

  public Checkout(Basket basket, DiscountManager discountManager) {
    this. basket = basket;
    this.discountManager = discountManager;
  }

  public BigDecimal getCost() {
    return subtotal().subtract(discounts());
  }

  // TODO: merge sub total and discount together
  private BigDecimal subtotal() {
    return basket.items().stream().map(Item::price)
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO)
        .setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal discounts() {
    Map<String, Integer> groupedItems = new HashMap<>();
    basket.items().forEach(i -> {
      int count = groupedItems.getOrDefault(i.getId(), 0) + 1;
      groupedItems.put(i.getId(), count);
    });

    BigDecimal newPrice = BigDecimal.ZERO;
    for (Map.Entry<String, Integer> entry : groupedItems.entrySet()) {
      String id = entry.getKey();
      Integer count = entry.getValue();
      newPrice = newPrice.add(discountManager.applyDiscount(id, count));
    }
    return newPrice;
  }
}
