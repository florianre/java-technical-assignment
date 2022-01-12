package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UnitDiscountRule {

  private List<String> forItems;
  private long discountFor;
  private BigDecimal discountedPrice;

  public BigDecimal getDiscountedPrice(Item item, int count) {
    if (count < discountFor){
      return BigDecimal.ZERO;
    } else {
      return BigDecimal.valueOf(count % discountFor)
          .multiply(discountedPrice).setScale(2, RoundingMode.HALF_UP);
    }
  }

  public UnitDiscountRule(List<String> forItems, int discountFor, BigDecimal discountedPrice) {
    this.forItems = forItems;
    this.discountFor = discountFor;
    this.discountedPrice = discountedPrice;
  }

  public void addItemToDiscountRule(String id) {
    forItems.add(id);
  }

  public void removeItemToDiscountRule(String id) {
    forItems.remove(id);
  }

}
