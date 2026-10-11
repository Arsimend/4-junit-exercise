package price.formatter;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {

    /**
     * Format the price to a string with the following format: 123 456,78 €.
     *
     * A space is used as thousand separator and a comma as decimal separator.
     * The decimal part must be rounded to two digits, unless the decimal part is 0,
     * in which case it is omitted. The currency symbol € is added at the end of the
     * string.
     *
     * @param price the price to format, as a double
     * @return the formatted price as a string
     */
    public String formatPrice(double price) {
        // https://stackoverflow.com/a/5054217
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMAN);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        return formatter
                .format(price)
                .replace(",00", "")
                .replace(".", " ")
                + " €";
    }
}
