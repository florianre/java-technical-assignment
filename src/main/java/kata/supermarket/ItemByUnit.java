package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final String id;

    ItemByUnit(final Product product, final String id) {
        this.product = product;
        this.id = id;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    @Override
    public String getId() {
        return id;
    }
}
