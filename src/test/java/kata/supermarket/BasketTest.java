package kata.supermarket;

import kata.supermarket.checkout.Basket;
import kata.supermarket.discount.DiscountRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    private static final String SWEETS = "sweets";
    private static final String DIGESTIVES = "digestives";
    private static final String PICK_AND_MIX = "pick-and-mix";

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items, Map<String, DiscountRule> discounts) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        discounts.forEach((id, discount) -> basket.addDiscount(id, discount));
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                twoOfTheSameItemForThePriceOfOne(),
                fourHundredGramsOfPickAndMixOfItemForHalfPrice(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments fourHundredGramsOfPickAndMixOfItemForHalfPrice() {
        Map<String, DiscountRule> discounts = new HashMap<>();
        discounts.put(PICK_AND_MIX, new DiscountRule(0.4, BigDecimal.valueOf(1.55)));
        return Arguments.of("400g for the price of 200g", "1.55",
            Arrays.asList(twoHundredGramsOfPickAndMix(), twoHundredGramsOfPickAndMix()), discounts);
    }

    private static Arguments twoOfTheSameItemForThePriceOfOne() {
        Map<String, DiscountRule> discounts = new HashMap<>();
        discounts.put(DIGESTIVES, new DiscountRule(2, BigDecimal.valueOf(1.55)));
        return Arguments.of("two of the same item for the price of one", "1.55",
            Arrays.asList(aPackOfDigestives(), aPackOfDigestives()), discounts);
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()),
            new HashMap<>());
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix()), new HashMap<>()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()), new HashMap<>());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()), new HashMap<>());
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList(), new HashMap<>());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf("milk");
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf(DIGESTIVES);
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"), SWEETS);
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"), PICK_AND_MIX);
    }
}