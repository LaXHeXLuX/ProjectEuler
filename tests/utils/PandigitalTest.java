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

        assertTrue(Pandigital.isPandigital(1, 2));
        assertFalse(Pandigital.isPandigital(2, 2));

        assertTrue(Pandigital.isPandigital(5, 3));
        assertFalse(Pandigital.isPandigital(6, 3));
        assertTrue(Pandigital.isPandigital(7, 3));
    }
    @Test
    void groupIsPandigital() {
        assertTrue(Pandigital.groupIsPandigital(new int[] {2, 43, 816, 597}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {918273645}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {9, 1, 8, 2, 7, 3, 6, 4, 5}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78, 91}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 780}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 781}));

        assertFalse(Pandigital.groupIsPandigital(new int[] {2, 4}, 5));
        assertTrue(Pandigital.groupIsPandigital(new int[] {2, 4, 8}, 5));
    }
}