
package price.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Write your tests for the PriceFormatter here. See the specification of the
 * PriceFormatter and formatPrice method in the PriceFormatter class and the
 * readme file.
 */
public class PriceFormatterTest {

    @Test
    void test1() {
        PriceFormatter formatter = new PriceFormatter();

        assertEquals("1 234,50 €", formatter.formatPrice(1234.5));
    }

    @Test
    void test2() {
        PriceFormatter formatter = new PriceFormatter();

        assertEquals("12,34 €", formatter.formatPrice(12.34));
    }

    @Test
    void test3() {
        PriceFormatter formatter = new PriceFormatter();

        assertEquals("3,15 €", formatter.formatPrice(3.146));
    }

    @Test
    void test4() {
        PriceFormatter formatter = new PriceFormatter();

        assertEquals("100 €", formatter.formatPrice(100.0));
    }

    @Test
    void test5() {
        PriceFormatter formatter = new PriceFormatter();

        assertEquals("5,50 €", formatter.formatPrice(5.5));
    }

@Test
void test6() {
    PriceFormatter formatter = new PriceFormatter();

    assertEquals("3,14 €", formatter.formatPrice(3.141));
}


}