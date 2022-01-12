package kata.supermarket.discount;

import java.util.ArrayList;
import java.util.List;

public class DiscountManager {

  private final List<UnitDiscountRule> discounts;

  public DiscountManager() {
    this.discounts = new ArrayList<>();
  }

  // TODO: check rule already exists -- add ruled to discounts
  public void addDiscount(UnitDiscountRule discount) {
    discounts.add(discount);
  }

  public void removeDiscount(UnitDiscountRule discount) {
    discounts.remove(discount);
  }

  public void clearDiscounts() {
    discounts.clear();
  }

}
