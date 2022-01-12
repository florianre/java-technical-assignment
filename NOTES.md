# Notes

My initial plan was to code up just one build the core code needed to have discounts for unit items. Hence, my code
incrementing the count by one in the discount method. I was planning on making it possible with weighed items, however,
I ran out of time.

I also started writing the skeleton code of the Checkout class, because I did not think keeping the discount logic in
the basket class made much sense. In my ideal design, the basket should just hold the items, not apply discounts or even
calculate the price. So I would have a Checkout class (along with the unit tests) that has a Basket and DiscountManager
class, and in that class, the total cost of the basket would be calculated.

The main modification I made to the Item classes is add an ID field so that we know which items are applied to which
discount. This has the drawback of making the discount to item relationship 1 to 1. I wanted to handle that case later,
if I had time.