package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UnitDiscountRule {

  private long discountFor;
  private BigDecimal discountedPrice;

  public BigDecimal getDiscountedPrice(int count) {
    if (count < discountFor){
      return BigDecimal.ZERO;
    } else {
      return BigDecimal.valueOf(count / discountFor)
          .multiply(discountedPrice).setScale(2, RoundingMode.HALF_UP);
    }
  }

  public UnitDiscountRule(int discountFor, BigDecimal discountedPrice) {
    this.discountFor = discountFor;
    this.discountedPrice = discountedPrice;
  }

}
