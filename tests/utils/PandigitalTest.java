package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandigitalTest {

    @Test
    void isPandigital() {
        assertFalse(Pandigital.isPandigital(12321));
        assertFalse(Pandigital.isPandigital(95768));
        assertTrue(Pandigital.isPandigital(123_456_789));
        assertTrue(Pandigital.isPandigital(987_654_321));
        assertFalse(Pandigital.isPandigital(1_234_567_890));
    }
}