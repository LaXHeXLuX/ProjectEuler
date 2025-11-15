package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandigitalTest {

    @Test
    void isPandigital() {
        for (int i = 0; i < 10; i++) {
            assertTrue(Pandigital.isPandigital(i, i, i));
        }
        assertFalse(Pandigital.isPandigital(0, 0, 1));
        assertFalse(Pandigital.isPandigital(0, 1, 0));
        assertFalse(Pandigital.isPandigital(12321));
        assertFalse(Pandigital.isPandigital(95768));
        assertTrue(Pandigital.isPandigital(95768, 5, 9));
        assertTrue(Pandigital.isPandigital(123_456_789));
        assertTrue(Pandigital.isPandigital(987_654_321));
        assertFalse(Pandigital.isPandigital(1_234_567_890));
        assertTrue(Pandigital.isPandigital(1_234_567_890, 0, 9));
        assertFalse(Pandigital.isPandigital(1_234_567_899, 0, 9));
        assertFalse(Pandigital.isPandigital(1_234_567_890, 1, 9));
    }
}