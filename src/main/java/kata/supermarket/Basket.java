package kata.supermarket;

import kata.supermarket.discount.DiscountManager;
import kata.supermarket.discount.UnitDiscountRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Basket {
    private final List<Item> items;
    private final DiscountManager discountManager;

    public Basket() {
        this.items = new ArrayList<>();
        this.discountManager = new DiscountManager();
    }

    public void addDiscount(UnitDiscountRule discount) {
        discountManager.addDiscount(discount);
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            Map<Item, Integer> groupedItems = new HashMap<>();
            items.forEach(i -> {
                int count = groupedItems.getOrDefault(i.getId(), 0) + 1;
                groupedItems.put(i, count);
            });

            return BigDecimal.ZERO;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
