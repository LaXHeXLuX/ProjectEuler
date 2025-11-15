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
    @Test
    void groupIsPandigital() {
        assertTrue(Pandigital.groupIsPandigital(new int[] {2, 43, 816, 597}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {918273645}));
        assertTrue(Pandigital.groupIsPandigital(new int[] {9, 1, 8, 2, 7, 3, 6, 4, 5}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78, 91}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 78}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 780}));
        assertFalse(Pandigital.groupIsPandigital(new int[] {12, 34, 56, 781}));
    }
}