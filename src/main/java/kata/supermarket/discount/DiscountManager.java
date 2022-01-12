package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.*;

public class DiscountManager {

  private final Map<String, DiscountRule> discounts;

  public DiscountManager() {
    this.discounts = new HashMap<>();
  }

  // TODO: check rule already exists -- add ruled to discounts
  public void addDiscount(String id, DiscountRule discount) {
    discounts.put(id, discount);
  }

  public void removeDiscount() {
  }

  public void clearDiscounts() {
    discounts.clear();
  }

  public BigDecimal applyDiscount(String id, Integer count) {
    Optional<DiscountRule> discount = Optional.ofNullable(discounts.get(id));
    if (!discount.isPresent()) {
      return BigDecimal.ZERO;
    } else {
      return discount.get().getDiscountedPrice(count);
    }
  }
}
