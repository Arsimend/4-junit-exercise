package refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the DayOfYear.dayOfYear method.
 */
public class DayOfYearTest {

    @Test
    void test1() {
        assertEquals(1, DayOfYear.dayOfYear(1, 1, 2023));
    }

    @Test
    void test2() {
        assertEquals(60, DayOfYear.dayOfYear(2, 29, 2020));
    }

    @Test
    void test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            DayOfYear.dayOfYear(2, 29, 2019);
        });
    }

    @Test
    void test4() {
        assertEquals(61, DayOfYear.dayOfYear(3, 1, 2020));
    }

    @Test
    void test5() {
        assertEquals(60, DayOfYear.dayOfYear(3, 1, 2019));
    }

    @Test
    void test6() {
        assertEquals(60, DayOfYear.dayOfYear(3, 1, 1900));
    }

    @Test
    void test7() {
        assertEquals(61, DayOfYear.dayOfYear(3, 1, 2000));
    }

    @Test
    void test8() {
        assertEquals(365, DayOfYear.dayOfYear(12, 31, 2023));
    }

    @Test
    void test9() {
        assertThrows(IllegalArgumentException.class, () -> {
            DayOfYear.dayOfYear(13, 1, 2023);
        });
    }

    @Test
    void test10() {
        assertThrows(IllegalArgumentException.class, () -> {
            DayOfYear.dayOfYear(1, 0, 2023);
        });
    }
}
