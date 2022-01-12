package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.*;

public class DiscountManager {

  private final Map<String, UnitDiscountRule> discounts;

  public DiscountManager() {
    this.discounts = new HashMap<>();
  }

  // TODO: check rule already exists -- add ruled to discounts
  public void addDiscount(String id, UnitDiscountRule discount) {
    discounts.put(id, discount);
  }

  public void removeDiscount() {
  }

  public void clearDiscounts() {
    discounts.clear();
  }

  public BigDecimal applyDiscount(String id, Integer count) {
    Optional<UnitDiscountRule> discount = Optional.ofNullable(discounts.get(id));
    if (!discount.isPresent()) {
      return BigDecimal.ZERO;
    } else {
      return discount.get().getDiscountedPrice(count);
    }
  }
}
