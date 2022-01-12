package kata.supermarket.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountRule {

  private double discountFor;
  private BigDecimal discountedPrice;

  public BigDecimal getDiscountedPrice(double count) {
    if (count < discountFor) {
      return BigDecimal.ZERO;
    } else {
      return BigDecimal.valueOf(count / discountFor)
          .multiply(discountedPrice).setScale(2, RoundingMode.HALF_UP);
    }
  }

  public DiscountRule(int discountFor, BigDecimal discountedPrice) {
    this.discountFor = discountFor;
    this.discountedPrice = discountedPrice;
  }

}
