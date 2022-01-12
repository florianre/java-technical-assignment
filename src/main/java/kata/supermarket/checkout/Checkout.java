package kata.supermarket.checkout;

import kata.supermarket.Item;
import kata.supermarket.discount.DiscountManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Checkout {

  private final Basket basket;
  private final DiscountManager discountManager;

  public Checkout(Basket basket, DiscountManager discountManager) {
    this. basket = basket;
    this.discountManager = discountManager;
  }

  public BigDecimal getCost() {
    return subtotal();
  }

  private BigDecimal subtotal() {
    return basket.items().stream().map(Item::price)
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO)
        .setScale(2, RoundingMode.HALF_UP);
  }
}
